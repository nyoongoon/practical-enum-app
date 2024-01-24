package goal.in.next.demo.service;

import java.util.Locale;

public class AbsUitilsChild extends AbsUtils{
    public String toLowerCase(String str){
        return str.toLowerCase(Locale.ROOT);
    }

    @Override
    public Character toCharacter(String str) {
        return null;
    }
}
