package com.singularity.ee;

import com.singularity.ee.controller.api.dto.transactionmonitor.ADStackTraceElement;
import com.singularity.ee.controller.api.dto.transactionmonitor.ADThrowable;
import com.singularity.ee.util.serialize.JavaSerializer;
import com.singularity.ee.util.serialize.ObjectSerializer;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

/**
 * Created by jun.ouyang on 7/27/16.
 */
public class NodeInfoReader {

    public static void main(String[] args) throws IOException {
        System.out.println("Usage: queriesFileName outputFileName host port");
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
            ResultSet rs;

            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            Map<String, String[]> queryAndNames = readQueriyAndNames(fileName);
            Map<Long, Map<String, String>> node2Values = new LinkedHashMap<>();
            PrintWriter outputWriter = new PrintWriter(new FileWriter(output, true), true);
            for (Map.Entry<String, String[]> entry : queryAndNames.entrySet()) {
                String[] valueNames = entry.getValue();
                rs = stmt.executeQuery(entry.getKey());
                while (rs.next()) {
                    Long nodeId = rs.getLong(0);
                    int i = 1;
                    final LinkedHashMap<String, String> values = new LinkedHashMap<>();
                    for (String name : valueNames) {
                        values.put(name, rs.getString(i++));
                    }
                    node2Values.merge(nodeId, values, (key, value) -> {
                        value.putAll(values);
                        return value;
                    });
                }
                rs.close();
            }
            outputWriter.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static Map<String, String[]> readQueriyAndNames(String fileName) throws IOException {
        Map<String, String[]> result = new LinkedHashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)))) {
            String names;
            while ((names = reader.readLine()) != null) {
                if (names.trim().length() == 0) continue;
                String[] nameArray = names.split(",");
                String query = reader.readLine();
                result.put(query, nameArray);
            }
        }
        return result;
    }

}
