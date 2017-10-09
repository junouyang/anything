import java.util.Optional;

/**
 * Created by jun.ouyang on 5/1/17.
 */
public class TestFinal {

    static class Test1 {
        private static final Test1 instance = new Test1();

        private Test1( ) {
            Test2.accessInstance();
        }
    }

    static class Test2 {

        public static void accessInstance() {
            System.out.println("In accessInstance() :" + String.valueOf(Test1.instance));
        }
    }


    public static void main(String[] args) {
        System.out.println(String.valueOf(Test1.instance));
        System.out.println(String.format("test a \"%s\"", 3));
        System.out.println(Optional.<String>ofNullable(null).isPresent());
    }
}
