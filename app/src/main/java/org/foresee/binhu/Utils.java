package org.foresee.binhu;

public class Utils {

    public static String join(String delimiter, String[] array) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            builder.append(array[i]);
            if (i != array.length - 1) {
                builder.append(delimiter);
            }
        }
        return builder.toString();
    }
}
