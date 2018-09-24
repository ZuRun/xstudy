import org.junit.Test;

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
}
