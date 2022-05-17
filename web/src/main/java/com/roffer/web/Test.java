package com.roffer.web;

import java.util.stream.Stream;

public class Test {
    private final static Object lock = new Object();

    public static void main(String[] args) {
//        eg1:
//        Stream.of("线程1","线程2").forEach(v->new Thread(v){
//            @Override
//            public void run(){
//                Test.sleep();
//            }
//        }.start());

//        eg2:
//        new Thread(){
//            @Override
//            public void run(){
//                Test.testWait();
//            }
//        }.start();
//        new Thread(){
//            @Override
//            public void run(){
//                Test.notifyWait();
//            }
//        }.start();

//        eg3:Thread.yield(),
//        暂停当前正在执行的线程对象（及放弃当前拥有的cup资源），并执行其他线程。
//        yield()做的是让当前运行线程回到可运行状态，以允许具有相同优先级的其他线程获得运行机会。
//        但是这并不意味着，下一次获取时间片的线程不是另一个线程，也有可能是使用yield的线程
//        在使用Thread.yield()之后恢复到就绪状态，但并不意味着会进入阻塞状态，而是继续参与争夺时间片的过程中
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"执行");
            System.out.println(Thread.currentThread().getName()+"执行结束");
        },"线程A").start();

        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"执行");
                System.out.println(Thread.currentThread().getName()+"执行结束");
            },"线程B"+i).start();
        }

    }

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

    private static void testWait(){
        synchronized (lock){
            try {
                System.out.println("等待唤醒中");
                lock.wait();
                System.out.println("wait被唤醒");
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    private static void notifyWait(){
        synchronized (lock){
            try {
                Thread.sleep(3000);
                lock.notify();
                System.out.println("休眠3秒后唤醒wait");
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
