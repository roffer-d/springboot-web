package com.roffer.web;

import java.util.stream.Stream;

public class Test {
    private final static Object lock = new Object();

    public static void main(String[] args) {
        Stream.of("线程1","线程2").forEach(v->new Thread(v){
            @Override
            public void run(){
                Test.sleep();
            }
        }.start());
    }

//    private static void testWait(){
//        synchronized (lock){
//            try {
//                System.out.println("等待唤醒中");
//            }catch (InterruptedException e){
//                e.printStackTrace();
//            }
//        }
//    }
    private static void sleep(){
        synchronized (lock){
            try{
                System.out.println(Thread.currentThread().getName()+"正在执行");
                Thread.sleep(3000);
//                lock.wait(3000);
                System.out.println(Thread.currentThread().getName()+"休眠结束");
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
