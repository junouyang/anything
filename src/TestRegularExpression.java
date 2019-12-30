import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * Created by jun.ouyang on 12/31/16.
 */
public class TestRegularExpression {
    private static final ThreadLocal<Boolean> acceptEventsThreadLocal =
            new ThreadLocal<Boolean>() {
                @Override
                protected Boolean initialValue() {
                    return Boolean.TRUE;
                }
            };

    public static void main(String[] args ) throws InterruptedException {
        Pattern pattern = Pattern.compile("(?:[a-fA-F0-9]{1,4}:){7}[a-fA-F0-9]{1,4}");
        Pattern ipv4Pattern = Pattern.compile("(?:(?:0|1[0-9]{0,2}|2|2[0-9]|2[0-4][0-9]|25[0-5])\\.){3}(?:0|1[0-9]{0,2}|2|2[0-9]|2[0-4][0-9]|25[0-5])");
//        {7}[a-fA-F0-9]{1,4}

//        System.out.println( pattern.matcher("0a:").matches());
//        System.out.println( pattern.matcher("0ab:").matches());
//        System.out.println( pattern.matcher("0abe:").matches());
//        System.out.println( pattern.matcher("aabe:").matches());
//        System.out.println( pattern.matcher("2001:0db8:85a3:0000:0000:8a2e:0370:7334").matches());

//        System.out.println(ipv4Pattern.matcher("0.1.2.19").matches());
//        System.out.println(ipv4Pattern.matcher("2.29.249.255").matches());
//        System.out.println(ipv4Pattern.matcher("2.29.249.01").matches());
//        System.out.println(ipv4Pattern.matcher("17").matches());
//        System.out.println(ipv4Pattern.matcher("17").matches());
//        System.out.println(ipv4Pattern.matcher("199").matches());
//        System.out.println(ipv4Pattern.matcher("2").matches());
//        System.out.println(ipv4Pattern.matcher("29").matches());
//        System.out.println(ipv4Pattern.matcher("249").matches());
//        System.out.println(ipv4Pattern.matcher("255").matches());
//        System.out.println(ipv4Pattern.matcher("256").matches());
//        System.out.println(ipv4Pattern.matcher("01").matches());


        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++ ) {
            executorService.submit(() -> {
                if(!acceptEventsThreadLocal.get()) {
                    System.out.println("Existing false");
                }
            });
        }
        executorService.awaitTermination(1000, TimeUnit.SECONDS);
    }
}
