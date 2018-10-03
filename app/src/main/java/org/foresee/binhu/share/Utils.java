package org.foresee.binhu.share;

public class Utils {
    public static String join(String delimiter, String... strings){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            builder.append(strings[i]);
            if(i!=strings.length-1){
                builder.append(delimiter);
            }
        }
        return builder.toString();
    }
    public static String join(String delimiter, Iterable<? extends String> strings) {
        StringBuilder builder = new StringBuilder();
        for (String str : strings) {
            builder.append(str);
            if (strings.iterator().hasNext()) {
                builder.append(delimiter);
            }
        }
        return builder.toString();
    }
}
