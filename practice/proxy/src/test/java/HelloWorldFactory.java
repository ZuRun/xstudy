import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author zurun
 * @date 2018/9/24 12:27:56
 */
public class HelloWorldFactory implements MethodInterceptor {
    private HelloWorld helloWorld;

    public HelloWorldFactory() {
        //加载需要创建子类的类
        Enhancer enhancer = new Enhancer();
        //设置代理目标
        enhancer.setSuperclass(HelloWorld.class);
        //设置回调
        enhancer.setCallback(this);

        enhancer.setClassLoader(HelloWorld.class.getClassLoader());
        //返回子类对象
        helloWorld = (HelloWorld) enhancer.create();
    }

    public static HelloWorld createObj() {
        return new HelloWorldFactory().helloWorld;
    }

    @Override
    public Object intercept(Object target, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("before method run...");
        return methodProxy.invokeSuper(target, args);
    }
}
