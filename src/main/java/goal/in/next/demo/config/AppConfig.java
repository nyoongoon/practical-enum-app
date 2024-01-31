package goal.in.next.demo.config;

import goal.in.next.demo.service.AbsUitilsChild;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class AppConfig {

    public AbsUitilsChild absUitilsChild(){
        return new AbsUitilsChild();
    }
}
