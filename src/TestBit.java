import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by jun.ouyang on 1/8/17.
 */
public class TestBit {
    private static String a;
    public static void main(String[] args) {
//        System.out.println( getBits(-1));
//        System.out.println( getBits(-2));

        System.out.println(Integer.highestOneBit(1));
        System.out.println(Integer.highestOneBit(2));
        System.out.println(Integer.highestOneBit(4));
        System.out.println(Integer.highestOneBit(8));
        System.out.println(Integer.highestOneBit(16));
//
//        System.out.println(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(30l));
//
//        int[] a = {1};
//        System.out.println(java.util.Arrays.toString(a));
//
//
//        Map<String, Set<String>> map = new HashMap<>();
//        map.computeIfAbsent("a", key -> new HashSet<>()).add("a");
//        map.computeIfAbsent("a", key -> new HashSet<>()).add("b");
//        System.out.println(map.get("a"));

        String empty = null, nonEmpty = "kakaka";
        long zero = 0, size = 0;
        long start = System.currentTimeMillis();
        for( int i = 0; i < 1000000; i++ ) {
            if( empty != null) {
                a = empty;
            }
            if( nonEmpty != null ) {
                a = nonEmpty;
            }
        }
        System.out.println("check: " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        for( int i = 0; i < 1000000; i++ ) {
            a = empty;
            a = nonEmpty;
        }
        System.out.println("no check: " + (System.currentTimeMillis() - start));
    }







    public static String getBits(int value ) {
        StringBuilder result = new StringBuilder();
        for( int i = 0; i < 32; i++ ) {
            result.append(String.valueOf(value & 1));
            value >>= 1;
        }
        return result.reverse().toString();
    }
}
