package cn.zull.xstudy.spring.test.xml;

import cn.zull.xstudy.spring.test.annotation.PersonConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author zurun
 * @date 2020/1/6 20:29:14
 */
public class PersonTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("myFirstSpringXmlConfig.xml");
        Person bean = context.getBean(Person.class);
        System.out.println(bean);

        AnnotationConfigApplicationContext annoContext = new AnnotationConfigApplicationContext(PersonConfig.class);
        Map<String, Person> beansOfType = annoContext.getBeansOfType(Person.class);
    }
}