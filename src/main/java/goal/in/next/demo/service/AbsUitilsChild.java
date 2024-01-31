package goal.in.next.demo.service;

import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class AbsUitilsChild extends AbsUtils{

    public static String toUpperCase(String str){
        return AbsUtils.toUpperCase(str) + "!";
    }

    public String toLowerCase(String str){
        return str.toLowerCase(Locale.ROOT);
    }

    @Override
    public Character toCharacter(String str) {
        return null;
    }
}
