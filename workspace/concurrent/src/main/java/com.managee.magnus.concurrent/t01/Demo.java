package com.managee.magnus.concurrent.t01;

public class Demo  extends Thread {

    @Override
    public void run() {
        System.out.println("Thread Running!");
    }

    public static void main(String[] args) {
        Demo demo = new Demo();
        demo.start();
    }
}
