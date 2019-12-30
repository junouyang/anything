
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

/**
 * Created by jun.ouyang on 12/31/16.
 */
public class TestArrayStream {
    public static void main(String[] args) throws IOException, CloneNotSupportedException {
//        System.out.println(String.format("kaka: %s", null));
//        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
//
//        ClassLoader current = contextClassLoader;
//        while(current != null) {
//            System.out.println(current);
//            current = current.getParent();
//        }
//
//        System.out.println("------------------");
//        current = TestArrayStream.class.getClassLoader();
//        while(current != null) {
//            System.out.println(current);
//            current = current.getParent();
//        }
//        Double d = 2d;
//        System.out.println(new Long(String.valueOf(d)));
//
//        Map<String, Long> map = new HashMap<>();
//        AtomicInteger a = new AtomicInteger(1);
//
//        long current = System.currentTimeMillis();
//        System.out.println("kaka_" + current + a.incrementAndGet() + "=====" + current + "_" + a.incrementAndGet());
//        System.out.println("kaka_" + current + a.incrementAndGet() + "=====" + current + "_" + a.incrementAndGet());
//
//        System.out.println(String.format("%s|%%", 1));
//
////        System.out.println(System.currentTimeMillis());
////        Thread.sleep(-100l);
////        System.out.println(System.currentTimeMillis());
//
//        System.out.println(-4 % 3);




//        Integer[] c = new Integer[]{2, 3, 1, 4, 5};
//        Arrays.sort(c, (o1, o2) -> o1 - o2);
//
//        TreeSet<Integer> treeSet = new TreeSet<>((o1, o2) -> o1 - o2);
//        Arrays.stream(c).forEach(e -> treeSet.add(e));
//        System.out.println(treeSet);


//        ObjectMapper mapper = new ObjectMapper();
//        String jsonInString = "{'name' : 'mkyong'}";
//        BTCallPathStats btCallPathStats = new BTCallPathStats();
//
////JSON from file to Object
//        System.out.println(PerformanceStats.class.getProtectionDomain().getCodeSource());
//        BTSegmentExitStat[] btSegmentExitStats = mapper.readValue(new File("response.json"), BTSegmentExitStat[].class);

//        Arrays.sort(new Integer[]{2, 1, 3, 4}, (Comparator<Integer>) (o1, o2) -> o1 - o2);
//        BTSegmentExitStat btSegmentExitStat = new BTSegmentExitStat();

//        Test test = null;
//        System.out.println("" + String.valueOf(test));
//        test = new Test();
//        test.s = "kaka";
//        test.i = 10;
//        System.out.println("" + String.valueOf(test));

//        PriorityQueue<int[]> stopAndPrice = new PriorityQueue<>((p1, p2)-> p1[1] - p2[1]);
//        stopAndPrice.add(new int[]{1, 3});
//        stopAndPrice.add(new int[]{1, 2});
//        stopAndPrice.add(new int[]{1, 1});
//
//        while(!stopAndPrice.isEmpty()) {
//            System.out.println(stopAndPrice.poll()[1]);
//        }


        Field field = new Field(1);
        Parent parent = new Parent(field);

        System.out.println(field);

        System.out.println(parent.clone().field);

    }

    static class Test {
        int i;
        String s;

        public String toString() {
            return s +  ":" + i;
        }
    }



    static class Field {
        int i = 0;

        public Field(int i) {
            this.i = i;
        }
    }

    public static class Parent implements Cloneable {
        Field field;

        public Parent(Field field) {
            this.field = field;
        }

        public Parent clone() throws CloneNotSupportedException {
            return (Parent) super.clone();
        }
    }


}
