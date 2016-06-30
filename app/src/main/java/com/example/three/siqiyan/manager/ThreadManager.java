package com.example.three.siqiyan.manager;

import android.os.StrictMode;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池管理者，管理应用的线程
 *
 * @author threes
 */
public class ThreadManager {
    public ThreadManager() {
    }

    public static ThreadManager instance = new ThreadManager();
    private ThreadPoolProxy longPool;//长线程池
    private ThreadPoolProxy shortPool;//短线程池

    public static ThreadManager getInstance() {
        return instance;
    }

    //合适的线程数=cpu核数+1

    /**
     * 创建长线程池
     * @return
     */
    public synchronized ThreadPoolProxy createLongPoll() {
        if (longPool == null) {
            longPool = new ThreadPoolProxy(5, 5, 5000L);
        }
        return longPool;
    }

    /**
     * 创建短线程池
     * @return
     */
    public synchronized ThreadPoolProxy createShortPoll() {
        if (shortPool == null) {
            longPool = new ThreadPoolProxy(3, 3, 5000L);
        }
        return shortPool;
    }

    /**
     * 内部线程池管理者
     */
    public class ThreadPoolProxy {
        ThreadPoolExecutor pool;
        private int poolSize;//线程数量
        private int maxPooxSize;//最大线程数量
        private long time;//没有任务执行，线程存活时间

        public ThreadPoolProxy(int poolSize, int maxPooxSize, long time) {
            this.poolSize = poolSize;
            this.maxPooxSize = maxPooxSize;
            this.time = time;
        }

        /**
         * 执行任务
         */
        public void excute(Runnable runnable) {
            if (pool == null) {
                /**参数：
                 * 1.先城池管理的线程数
                 * 2.如果排满，额外创建的线程数
                 * 3.如果线程没有要执行的任务存货的时间
                 * 4.时间单位
                 * 5.如果所有的线程都被使用，其他任务存到LinkedBlockingQueue栈中
                 */
                pool = new ThreadPoolExecutor(poolSize, maxPooxSize, time, TimeUnit.MICROSECONDS, new LinkedBlockingQueue<Runnable>(10));
            }
            pool.execute(runnable);//调用线程池执行异步任务
        }

        /**
         * 取消任务
         *
         * @param runnable
         */
        public void cancle(Runnable runnable) {
            if (pool != null && pool.isShutdown() & !pool.isTerminated()) {
                pool.remove(runnable);//取消异步任务
            }
        }
    }

}
