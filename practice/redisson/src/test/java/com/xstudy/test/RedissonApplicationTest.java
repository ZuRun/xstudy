package com.xstudy.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StopWatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * @author zurun
 * @date 2018/10/14 21:52:36
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RedissonApplication.class)
public class RedissonApplicationTest {

    @Autowired
    RedissonClient redisClient;

    @Test
    public void set() {
        RBucket rBucket = redisClient.getBucket("redisson");
        rBucket.set(1);
        System.out.println(rBucket.get());
    }

    @Test
    public void test() throws InterruptedException {
        int num = 10;
        CountDownLatch countDownLatch = new CountDownLatch(num);
        ExecutorService executorService = Executors.newFixedThreadPool(10, r -> new Thread(r, "thread-pool"));

        Runnable runnable = () -> {
            RBucket rBucket = redisClient.getBucket("redisson");
            for (int i = 0; i < 1000; i++) {
                rBucket.set("val:" + i);
            }
            countDownLatch.countDown();
        };
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0; i < num; i++) {
            executorService.execute(runnable);
        }

//        new Thread(() -> {
//            RBucket rBucket = redisClient.getBucket("redisson");
//            for (int i = 0; i < 1000; i++) {
//                rBucket.set("val:" + i);
//                System.out.println("i=" + i + ":" + rBucket.get());
//            }
//        }).start();


        countDownLatch.await();
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
    }
}