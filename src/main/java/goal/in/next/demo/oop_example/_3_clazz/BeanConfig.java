package goal.in.next.demo.oop_example._3_clazz;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    public Scale scale(){
//        return new BalanceScale();
        return new BalanceScaleOnMars(); //의존성 주입 교체
        StringUtils

    }
}
