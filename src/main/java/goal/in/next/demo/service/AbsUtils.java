package goal.in.next.demo.service;



import nonapi.io.github.classgraph.utils.StringUtils;

import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;

public abstract class AbsUtils {
    public static String toUpperCase(String str){
        return str.toUpperCase(Locale.ROOT);
    }
    public Integer toInteger(String str){ // 역할이 불분명...
        return Integer.parseInt(str);
    }
    public abstract Character toCharacter(String str);
}
