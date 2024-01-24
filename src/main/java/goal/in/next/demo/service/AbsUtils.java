package goal.in.next.demo.service;

import java.util.Locale;

public abstract class AbsUtils {
    public static String toUpperCase(String str){
        return str.toUpperCase(Locale.ROOT);
    }
    public Integer toInteger(String str){
        return Integer.parseInt(str);
    }
    public abstract Character toCharacter(String str);
}
