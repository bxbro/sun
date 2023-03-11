package com.bxbro.sun.platform.service.blackhorse;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * TODO
 *
 * @author: dong
 * @date: 2023/3/11 18:19
 * @since: 1.0
 */
@Slf4j
public class ReentrantLockTest {
    private static ReentrantLock lock = new ReentrantLock();
    static Condition cigaretteCond = lock.newCondition();
    static Condition takeoutCond = lock.newCondition();
    // 是否有香烟
    static boolean hasCigarette = false;
    // 是否有外卖
    static boolean hasEatings = false;

    public static void main(String[] args) {
        new Thread(()->{
            lock.lock();
            try{
                while (!hasCigarette) {
                    try {
                        log.debug("没有烟，等待中");
                        cigaretteCond.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("烟到了，开始干活...");
            } finally {
                lock.unlock();
            }
        }, "小南").start();

        new Thread(()->{
            lock.lock();
            try{
                while (!hasEatings) {
                    try {
                        log.debug("没有外卖，等待中");
                        takeoutCond.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("外卖到了，开始干活...");
            } finally {
                lock.unlock();
            }
        }, "小女").start();

        new Thread(()->{
            boolean hasLock = lock.tryLock();
            if (!hasLock) {
                log.debug("未获得锁，退出...");
                return;
            }
            try {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    hasCigarette = true;
                    cigaretteCond.signal();
                    log.debug("烟到了...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                lock.unlock();
            }
        }, "跑腿的").start();

        new Thread(()->{
            lock.lock();
            try {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    hasEatings = true;
                    takeoutCond.signal();
                    log.debug("外卖到了...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                lock.unlock();
            }
        }, "外卖小哥").start();
    }
}
