package com.bxbro.sun.platform.service.blackhorse;

/**
 * 死锁测试
 *
 * @author: dong
 * @date: 2023/3/8 20:59
 * @since: 1.0
 */
public class DeadLockTest {
    public static void main(String[] args) {
        Object A = new Object();
        Object B = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (A) {
                System.out.println("lock A ===");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (B) {
                    System.out.println("lock B");
                }
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            synchronized (B) {
                System.out.println("lock B ===");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (A) {
                    System.out.println("lock A");
                }
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
