package com.xstudy.test.redis;

import org.junit.Test;
import org.junit.rules.Stopwatch;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StopWatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.*;

/**
 * @author zurun
 * @date 2018/10/14 23:35:13
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RedisApplication.class)
public class RedisApplicationTest {

    @Autowired
    RedisUtils redisUtils;

    @Test
    public void test() throws InterruptedException {
        int num = 10;
        CountDownLatch countDownLatch = new CountDownLatch(num);
        ExecutorService executorService = Executors.newFixedThreadPool(10, r -> new Thread(r, "thread-pool"));

        Runnable runnable = () -> {
            for (int i = 0; i < 1000; i++) {
                redisUtils.set("redis", "val:" + i);
            }
            System.out.println(Thread.currentThread().getName());
            countDownLatch.countDown();
        };
        for (int i = 0; i < num; i++) {
            executorService.execute(runnable);
        }
//        new Thread(() -> {
//            StopWatch stopWatch = new StopWatch();
//            stopWatch.start();
//            for (int i = 0; i < 1000; i++) {
//                redisUtils.set("redis", "val:" + i);
//                System.out.println("i=" + i + ":" + redisUtils.getString("redis"));
//            }
//            stopWatch.stop();
//            System.out.println(stopWatch.getTotalTimeMillis());
//        }).start();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        countDownLatch.await();
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
    }
}