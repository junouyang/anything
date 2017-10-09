import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by jun.ouyang on 12/31/16.
 */
public class TestArrayStream {
    public static void main(String[] args) {
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


        Set<String> strs = new HashSet<>();
        strs.add("1");
        strs.add("a");

        Map<Long, String> map = strs.stream().collect(Collectors.toMap(str -> {
            try {
                return Long.parseLong(str);
            } catch (Exception e) {
                return null;
            }
        }, str -> str));

        System.out.println(map);
    }
}
