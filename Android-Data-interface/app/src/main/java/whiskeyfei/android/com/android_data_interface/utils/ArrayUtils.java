package whiskeyfei.android.com.android_data_interface.utils;


public class ArrayUtils {

    private ArrayUtils() {
        throw new AssertionError();
    }

    public static <V> boolean isEmpty(V[] sourceArray) {
        return (sourceArray == null || sourceArray.length == 0);
    }

}
