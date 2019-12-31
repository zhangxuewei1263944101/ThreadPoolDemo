package com.pool.thread;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableScheduling
public class ThreadApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThreadApplication.class, args);
    }


    @Value("${threadPoolSowTask.pool.corePoolSize}")
    private Integer THREADPOOLSOWTASK_POOL_COREPOOLSIZE;

    @Value("${threadPoolSowTask.pool.maximumPoolSize}")
    private Integer THREADPOOLSOWTASK_POOL_MAXIMUMPOOLSIZE;

    @Value("${threadPoolSowTask.pool.keepAliveTime}")
    private Long THREADPOOLSOWTASK_POOL_KEEPALIVETIME;

    @Value("${threadPoolSowTask.pool.workQueue}")
    private Integer THREADPOOLSOWTASK_POOL_WORKQUEUE;
    @Bean
    public ThreadPoolExecutor threadPoolSowTask() {
        ThreadPoolExecutor threadPoolSowTask = new ThreadPoolExecutor(
                THREADPOOLSOWTASK_POOL_COREPOOLSIZE,//corePoolSize保持在池中的线程数，即使它们是空闲的
                THREADPOOLSOWTASK_POOL_MAXIMUMPOOLSIZE,//maximumPoolSize池中允许的最大线程数
                THREADPOOLSOWTASK_POOL_KEEPALIVETIME,//keepAliveTime当线程数量大于内核时，这是多余的空闲线程在终止新任务之前等待的最大时间。
                TimeUnit.MINUTES,//单位是{@code keepAliveTime}参数的时间单位
                new LinkedBlockingQueue<Runnable>(THREADPOOLSOWTASK_POOL_WORKQUEUE),//用于在任务执行之前保存它们的队列。这个队列将只包含由{@code execute}方法提交的{@code Runnable}任务。
                new DaemonThreadFactory("sowTask"),//执行程序创建新线程时使用的工厂
                new ThreadPoolExecutor.CallerRunsPolicy());//当执行被阻塞时使用的处理程序，因为达到了线程界限和队列容量

        return threadPoolSowTask;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
