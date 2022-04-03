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
    // 队列达到最大值时的拒绝策略
    private static RejectedExecutionHandler handler = null;

    static {
        handler = new TxThreadPoolExecutorUtils.RejectHandler();
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
     * @param corePool
     * @param maxPoolSize
     * @param keepLive
     * @param unit
     * @param queue
     * @param threadFactory
     * @param handler
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
