package com.example.demo.executorpool;

import java.util.concurrent.*;

public class Student {

    private static ExecutorService executor = Executors.newFixedThreadPool(10);

    private static ThreadPoolExecutor service = new ThreadPoolExecutor(3,5,
            60, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(10),new MyRejectedPolicyHandler());


    public static void main(String[] args) throws Exception{
        for (int i = 0; i < 100; i++) {
            final String j = "这是第J个数" + i;
            service.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(100);
                        System.out.println("线程名称 ： =============》 " + Thread.currentThread().getName() + "j ===" + j);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        while (true){
            //System.out.println("queue size ===      " + service.getQueue().size());
            Thread.sleep(1000);
        }
    }

    @Override
    public String toString() {
        return "name " + "age";
    }
}
