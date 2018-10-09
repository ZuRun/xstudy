package cn.zull.study.thread.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zurun
 * @date 2018/10/9 11:38:36
 */
public class MonitorableThreadPoolExecutor extends ThreadPoolExecutor {
    private MonitorHandler monitorHandler;

    public MonitorableThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        if(monitorHandler!=null){

        }
        System.out.println("++");
    }

    public void addMonitorTask(MonitorHandler monitorHandler) {
        this.monitorHandler = monitorHandler;
    }


}
