package com.bxbro.sun.platform.service.blackhorse;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * 线程池测试用例
 *
 * @author: dong
 * @date: 2023/3/18 16:34
 * @since: 1.0
 */
public class ThreadPoolTest {

    public static void main(String[] args) {
        // 固定线程池
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        // 缓冲线程池
        ExecutorService executorService1 = Executors.newCachedThreadPool();
        // 单线程线程池
        ExecutorService executorService2 = Executors.newSingleThreadExecutor();

    }
}
