package whiskeyfei.android.com.android_data_interface.utils;

public class StringUtils {
    private StringUtils() {
        throw new AssertionError();
    }

    public static boolean isEmpty(CharSequence str) {
        return (str == null || str.length() == 0);
    }

}
