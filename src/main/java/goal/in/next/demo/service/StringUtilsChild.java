package goal.in.next.demo.service;

import org.apache.commons.lang3.StringUtils;

import java.util.Locale;

public class StringUtilsChild extends StringUtils {

    public static String toUpperCase(String str){
        return str.toUpperCase(Locale.ROOT);
    }
}
