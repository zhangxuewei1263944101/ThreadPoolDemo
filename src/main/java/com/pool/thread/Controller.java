package com.pool.thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/banYun")
public class Controller {

    @Autowired
    SonTaskService sonTaskService;

    @RequestMapping("/yuMi")
    public void 搬运玉米(){
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date())+ " 黑驴开始卸货了，线程ID："+Thread.currentThread().getId());
        sonTaskService.task(Thread.currentThread().getId());
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date())+ " 黑驴回家了！线程ID："+Thread.currentThread().getId());
    }
}
