import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * Created by jun.ouyang on 2/23/17.
 */
public class TestRandom {
    public static void main(String[] args) {
//        System.out.println("ip-10-130-65-28.eu-west-1.compute.internal".equals("ip-10-130-65-28.eu-west-1.compute.internal"));
//        System.out.println(UUID.randomUUID().toString());
//
//        int[] array = {1, 2, 3};
//        Arrays.stream(array).anyMatch(v -> v == 2);

//        List<Character> chList = new LinkedList<>();
        for( int i = 1; i <= 16; i++) {
            System.out.println(i + (i & -i));
        }
    }
}
