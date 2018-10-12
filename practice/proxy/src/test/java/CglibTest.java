import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zurun
 * @date 2018/9/24 11:45:01
 */
//@RunWith(SpringJUnit4ClassRunner.class)
public class CglibTest {

    @Test
    public void test1() {
        HelloWorldFactory.createObj().sayHello();
    }

    public static void main(String[] args) {
        A a = new A();
        a.setName("zurun");
        a.setData(new HashMap());
        B b = JSON.parseObject(a.toString(), B.class);
        System.out.println(b.toString());
    }

    @Data
    static class A {
        private String name;
        private Map data;

        @Override
        public String toString() {
            return JSON.toJSONString(this);
        }
    }

    @Data
    static class B {
        private String name;
        private String data;
        @Override
        public String toString() {
            return JSON.toJSONString(this);
        }
    }

}
