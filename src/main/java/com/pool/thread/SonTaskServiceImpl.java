package com.pool.thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;

@Service
public class SonTaskServiceImpl implements SonTaskService {
    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;

    public void task(Long controllerId){
        FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                大米(controllerId);
                return null;
            }
        });
        threadPoolExecutor.execute(futureTask);
    }

    void 大米(Long controllerPid){
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date())+ " 骡子-搬！线程号："+Thread.currentThread().getId()+"--对应的驴线程号："+controllerPid);
        try {
            Thread.sleep((long) 5000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date())+ " 骡子-搬运完毕！线程号："+Thread.currentThread().getId()+"--对应的驴线程号："+controllerPid);
    }

}
