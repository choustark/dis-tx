package com.chou.common_module.utils;


import java.util.concurrent.*;

/**
 * @ClassName ThreadPoolUtils
 * @Description 线程池工具类
 * @Author Axel
 * @Date 2021/9/12 23:30
 * @Version 1.0
 */

public class TxThreadPoolExecutorUtils{

    // 核心线程数
    private static Integer corePoolSize = 5;
    // 最大的线程数
    private static Integer maxPoolSize = 10;
    // 当最大线程数大于核心线程数时，其余的线程多久没有被调用释放的时间
    private static long keepAliveTime = 1000;
    // keepLive 单位
    private static TimeUnit timeUnit;
    // 队列
    private static BlockingQueue queue =  new LinkedBlockingDeque(10);;
    // 线程工厂
    private static ThreadFactory threadFactory;
    // executor
    private static ExecutorService executor;
    // 队列达到最大值时的拒绝策略
    private static RejectedExecutionHandler handler;



    static {

        handler = new ThreadPoolExecutor.AbortPolicy();
        executor = new ThreadPoolExecutor(corePoolSize,maxPoolSize,1000,TimeUnit.SECONDS,queue,threadFactory,handler);
    }


}
