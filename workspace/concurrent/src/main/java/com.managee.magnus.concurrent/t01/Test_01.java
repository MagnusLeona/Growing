package com.managee.magnus.concurrent.t01;

import java.util.concurrent.TimeUnit;

/*
synchronize(this)和synchronize方法，都是锁当前对象
 */
public class Test_01 {
    private int count = 0;
    private Object o = new Object();

    public void testSync1() {
        synchronized (o) {
            System.out.println(Thread.currentThread().getName() + "count = " + count++);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void testSync2() {
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + "count = " + count++);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void testSync3() {
        System.out.println(Thread.currentThread().getName() + "count = " + count++);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        Test_01 test_01 = new Test_01();

        new Thread(new Runnable() {
            @Override
            public void run() {
                test_01.testSync1();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                test_01.testSync2();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                test_01.testSync3();
            }
        }).start();
    }
}
