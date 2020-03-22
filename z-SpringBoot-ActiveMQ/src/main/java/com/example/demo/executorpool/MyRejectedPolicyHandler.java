package com.example.demo.executorpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class MyRejectedPolicyHandler implements RejectedExecutionHandler {

    public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
        System.out.println("执行拒绝策略 ==========" + e.getQueue().size());
        if (!e.isShutdown()) {
            e.getQueue().poll();
            e.execute(r);
        }

    }

}
