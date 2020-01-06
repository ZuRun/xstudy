package cn.zull.xstudy.spring.test.annotation;

import cn.zull.xstudy.spring.test.xml.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * @author zurun
 * @date 2020/1/6 20:56:53
 */
@Configuration
@ComponentScan(includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION,),
        @ComponentScan.Filter
})
public class PersonConfig {

    @Bean
    public Person p() {
        return new Person();
    }
}
