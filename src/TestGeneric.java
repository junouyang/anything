import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by jun.ouyang on 2/15/17.
 */
public class TestGeneric {

    public static abstract class A<T extends Object> {
        public A() {
            Class clazz = this.getClass();
            Type genericSupserClass = null;
            while(clazz != null) {
                genericSupserClass = clazz.getGenericSuperclass();
                if( genericSupserClass instanceof ParameterizedType ) break;
                clazz = clazz.getSuperclass();
            }
            Type[] configTypes = ((ParameterizedType) genericSupserClass).getActualTypeArguments();
            System.out.println(configTypes[0].toString());
        }
    }

    public static abstract class B<T extends Object> extends A<T> {

    }

    public static class C extends A<String> {

    }

    public static class D extends C {

    }

    public static void main(String[] args ) {
        new D();
    }
}
