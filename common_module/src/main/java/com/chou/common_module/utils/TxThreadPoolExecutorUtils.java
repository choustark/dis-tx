package com.chou.common_module.utils;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @ClassName ThreadPoolUtils
 * @Description 线程池工具类
 * @Author Axel
 * @Date 2021/9/12 23:30
 * @Version 1.0
 */

@Slf4j
public class TxThreadPoolExecutorUtils{

    // 核心线程数
    private static final Integer corePoolSize = 5;
    // 最大的线程数
    private static final Integer maxPoolSize = 10;
    // 当最大线程数大于核心线程数时，其余的线程多久没有被调用释放的时间
    private static final long keepAliveTime = 1000;
    // keepLive 单位
    private static TimeUnit timeUnit;
    // 队列存放线程
    private static final BlockingQueue<Runnable> queue = new LinkedBlockingDeque<>(10);
    // 线程工厂
    private static final ThreadFactory threadFactory = Executors.defaultThreadFactory();
    // executor
    private static ExecutorService executor = null;

    static {
        // 队列达到最大值时的拒绝策略
        RejectedExecutionHandler handler = new RejectHandler();
        executor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, 1000, TimeUnit.SECONDS, queue, threadFactory, handler);
    }

    public  static ExecutorService getExecutor(){
        return executor;
    }


    /**
     *
     * @param threads
     * @return
     */
    public static ExecutorService newFixedThreadPool(Integer threads){
       return Executors.newFixedThreadPool(threads);
    }

    /**
     * @return
     */
    public static ExecutorService newCachedThreadPool(){
        return Executors.newCachedThreadPool();
    }

    /**
     * @return
     */
    public static ExecutorService newSingleThreadExecutor(){
        return Executors.newSingleThreadExecutor();
    }

    public static ExecutorService newDefaultThreadPool(){
        return executor;
    }

    /**
     *  自定义线程池参数
     * @param corePool 核心线程数量
     * @param maxPoolSize 线程池中最大可以持有多少线程
     * @param keepLive 存活时间
     * @param unit  存活时间单位
     * @param queue 延迟队列
     * @param threadFactory 线程工厂
     * @param handler 队列满时处理策略
     * @return
     */
    public static ExecutorService newTreadThreadPool(int corePool,
                                                     int maxPoolSize,
                                                     long keepLive,
                                                     TimeUnit unit,
                                                     BlockingQueue<Runnable> queue,
                                                     ThreadFactory threadFactory,
                                                     RejectedExecutionHandler handler){
        return new ThreadPoolExecutor(corePool,maxPoolSize,keepLive,unit,queue,threadFactory,handler);
    }

    private static class RejectHandler implements RejectedExecutionHandler{
        private RejectHandler() {
        }

        public void rejectedExecution(Runnable r, java.util.concurrent.ThreadPoolExecutor executor) {
            throw new RejectedExecutionException();
        }
    }
}
