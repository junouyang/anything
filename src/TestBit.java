/**
 * Created by jun.ouyang on 1/8/17.
 */
public class TestBit {
    public static void main(String[] args) {
//        System.out.println( getBits(-1));
//        System.out.println( getBits(-2));

        System.out.println(Integer.highestOneBit(1));
        System.out.println(Integer.highestOneBit(2));
        System.out.println(Integer.highestOneBit(4));
        System.out.println(Integer.highestOneBit(8));
        System.out.println(Integer.highestOneBit(16));
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
