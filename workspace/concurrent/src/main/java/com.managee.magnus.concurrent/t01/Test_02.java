package com.managee.magnus.concurrent.t01;

import java.util.concurrent.TimeUnit;

public class Test_02 {
    public static int staticCount = 0;

    public static synchronized void testSync1() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + "count = " + staticCount++);
        TimeUnit.SECONDS.sleep(3);
    }

    public static void testSync2() throws InterruptedException {
        synchronized (Test_02.class) {
            System.out.println(Thread.currentThread().getName() + "count = " + staticCount++);
            TimeUnit.SECONDS.sleep(3);
        }
    }

    public static void main(String[] args) {
        final Test_02 test_02 = new Test_02();
         new Thread(new Runnable() {
             @Override
             public void run() {
                 try {
                     Test_02.testSync1();
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
             }
         }).start();

         new Thread(new Runnable() {
             @Override
             public void run() {
                 try {
                     Test_02.testSync2();
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
             }
         }).start();
    }
}
