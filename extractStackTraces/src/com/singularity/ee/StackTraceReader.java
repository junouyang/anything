package com.singularity.ee;

import com.singularity.ee.controller.api.dto.transactionmonitor.ADStackTraceElement;
import com.singularity.ee.controller.api.dto.transactionmonitor.ADThrowable;
import com.singularity.ee.util.serialize.JavaSerializer;
import com.singularity.ee.util.serialize.ObjectSerializer;

import java.io.*;
import java.sql.*;
import java.util.*;

/**
 * Created by jun.ouyang on 7/27/16.
 */
public class StackTraceReader {

    static List<Filter> filters = new ArrayList<>();
    static List<Filter> verifiedFilters = new ArrayList<>();

    static {
        filters.add(new Filter() {
            public boolean match(String className, String simpleName) {
                return className.contains("$$EnhancerByCGLIB$$");
            }
        });
        filters.add(new Filter() {
            public boolean match(String className, String simpleName) {
                return className.startsWith("com.sun.proxy.$Proxy");
            }
        });
        filters.add(new Filter() {
            public boolean match(String className, String simpleName) {
                return className.contains("$$EnhancerBySpringCGLIB$$");
            }
        });
        filters.add(new Filter() {
            public boolean match(String className, String simpleName) {
                return className.startsWith("sun.reflect.Generated");
            }
        });
        filters.add(new Filter() {
            public boolean match(String className, String simpleName) {
                return (className.startsWith("com.comcast") && simpleName.contains("_EOImpl"));
            }
        });
        filters.add(new Filter() {
            public boolean match(String className, String simpleName) {
                return className.contains("$$FastClassByCGLIB$$");
            }
        });
        filters.add(new Filter() {
            public boolean match(String className, String simpleName) {
                return ((className.startsWith("weblogic") || className.startsWith("amdocs")) && className.endsWith("_WLStub"));
            }
        });
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Usage: allFileName outputFileName host port limit");
        String fileName = args[0];
        String output = args[1];
        String host = args[2];
        String port = args[3];

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String database = "controller";
            String url = "jdbc:mysql://" + host + ":" + port + "/" + database;
            String user = "controller";
            String password = "controller";
            boolean isOver = false;
            ResultSet rs;
            ObjectSerializer objectSerializer = new JavaSerializer();

            int count = Integer.parseInt(System.getProperty("offset"));
            int step = Integer.parseInt(System.getProperty("step"));
            boolean filter = "true".equalsIgnoreCase(System.getProperty("filter"));
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            while (!isOver && count * step < 3300000) {
                PrintWriter totalWriter = new PrintWriter(new FileWriter(fileName, true), true);
                PrintWriter outputWriter = new PrintWriter(new FileWriter(output, true), true);
                Map<String, Map<String, String>> map = new HashMap<>();
                Set<String> found = new HashSet<>();
                System.out.println("--------------- " + count);
                if (args.length == 4) {
                    int start = count * step;
                    count++;
                    rs = stmt.executeQuery("select fc.content from application_diagnostic_data a join file f on a.details_id = f.id join file_content fc on fc.id = f.file_content_id where a.diagnostic_type = 'STACK_TRACE' and fc.id between " + start + " and " + (start + step) + ";");
                } else {
                    rs = stmt.executeQuery("select fc.content from application_diagnostic_data a join file f on a.details_id = f.id join file_content fc on fc.id = f.file_content_id where a.diagnostic_type = 'STACK_TRACE' limit " + args[4] + ";");
                    isOver = true;
                }
                while (rs.next()) {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];

                    InputStream is = rs.getBlob("content").getBinaryStream();
                    int length;
                    while ((length = is.read(buffer)) != -1) {
                        baos.write(buffer, 0, length);
                    }
                    is.close();
                    try {
                        ADThrowable[] throwables = (ADThrowable[]) objectSerializer.unmarshalObject(baos.toByteArray());
                        for (ADThrowable throwable : throwables) {
                            for (ADStackTraceElement trace : throwable.getAdStackTraceElements()) {
                                String className = trace.getClassName();
                                String total = className + "," + trace.getMethodName() + "," + trace.getFileName() + "," + trace.getLineNumber();
                                totalWriter.println(total);
                                String traceFileName = trace.getFileName();
                                if (trace.getLineNumber() != -1 || (traceFileName != null && !traceFileName.contains("generated"))) {
                                    continue;
                                }
                                int endIndex = className.lastIndexOf(".");
                                if (endIndex == -1) {
                                    continue;
                                }
                                String simpleName = className.substring(endIndex + 1);
//                                if (filter) {
//                                    if (className.startsWith("com.sun.proxy.$Proxy")
//                                            || className.contains("$$EnhancerByCGLIB$$")
//                                            || className.contains("$$EnhancerBySpringCGLIB$$")
//                                            || className.startsWith("sun.reflect.Generated")
//                                            || (className.startsWith("com.comcast") && simpleName.contains("_EOImpl"))
//                                            || className.contains("$$FastClassByCGLIB$$")
//                                            || ((className.startsWith("weblogic") || className.startsWith("amdocs")) && className.endsWith("_WLStub"))) {
//                                        continue;
//                                    }
//                                }
                                if( filter(className, simpleName, verifiedFilters, null )) continue;
                                totalWriter.println(total);
                                int simpleNameLength = simpleName.length();
                                String key1 = simpleName.substring(0, 1) + simpleNameLength;
                                String pkg = className.substring(0, endIndex);
                                String key = pkg + ":" + String.valueOf(trace.getMethodName());
                                String dupKey = pkg + "." + key1 + ":" + String.valueOf(trace.getMethodName());
                                if (found.contains(dupKey)) {
                                    continue;
                                }
                                Map<String, String> l2trace = map.get(key);
                                if (l2trace != null && l2trace.containsKey(key1)) {
                                    String oldName = l2trace.get(key1);
                                    if (!simpleName.equals(oldName)) {
                                        String first = pkg + "." + oldName + ", " + traceFileName;
                                        String second = pkg + "." + simpleName + ", " + traceFileName;
                                        filter(className, simpleName, filters, verifiedFilters);
                                        System.out.println(first);
                                        System.out.println(second);
                                        System.out.println();
                                        outputWriter.println(first);
                                        outputWriter.println(second);
                                        outputWriter.println();
                                        found.add(dupKey);
                                        map.remove(key);
                                        continue;
                                    } else {
                                        map.remove(key);
                                    }
                                }
                                if (l2trace == null) {
                                    l2trace = new HashMap<>();
                                    map.put(key, l2trace);
                                }
                                l2trace.put(key1, simpleName);
                            }
                            totalWriter.println("----------");
                        }
                        totalWriter.println("=============");
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
                rs.close();
                totalWriter.close();
                outputWriter.close();
                System.out.println(map.size() + ": " + found.size() + ", verified : " + verifiedFilters.size() + ", remain : " + filters.size());
            }
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static boolean filter( String className, String simpleName, List<Filter> source, List<Filter> destination ) {
        Iterator<Filter> iterator = source.iterator();
        while( iterator.hasNext() ) {
            Filter next = iterator.next();
            if( next.match(className, simpleName)) {
                if(destination != null ) {
                    destination.add(next);
                    iterator.remove();
                }
                return true;
            }
        }
        return false;
    }

    public interface Filter {
        boolean match(String className, String simpleName);
    }
}
