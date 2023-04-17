package com.bxbro.sun.platform.service.blackhorse;

import java.util.concurrent.Semaphore;

/**
 * TODO
 *
 * @author: dong
 * @date: 2023/4/2 13:09
 * @since: 1.0
 */
public class SemaphoreTest {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        semaphore.release();

    }
}
