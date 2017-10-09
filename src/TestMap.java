import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by jun.ouyang on 6/26/17.
 */
public class TestMap {

    public static class Test {
        int id;
        String name;

        public Test(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String toString() {
            return id + ":" + name;
        }
    }

    public static void main(String[] args) {
        List<Test> list = new ArrayList<>();
        list.add(new Test(1, "kakaka"));
        list.add(new Test(1, "heieh"));
        list.add(new Test(2, "hengheng"));
        list.add(new Test(2, "xixi"));

//        Map<Integer, Set<Test>> id2Names = list.stream().collect(Collectors
//                .groupingBy(Test::getId, Collector.of(0, (a, t) -> )));
//        System.out.println(id2Names);
    }
}
