//package com.ets.ets_backend.config;
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableAsync;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//
//import java.util.concurrent.Executor;
//import java.util.concurrent.ScheduledThreadPoolExecutor;
//import java.util.concurrent.ThreadPoolExecutor;
//
//@Configuration
//@EnableAsync
//public class MultiTConf {
//
//    @Bean(name = "taskExecutor")
//    public Executor taskExecutor(){
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setCorePoolSize(3); // number of threads, the minimum that can be token in pool.
//        executor.setMaxPoolSize(50); // Max size of the tread. time and thread scheduling? ...
//        executor.setQueueCapacity(500); // The queue to wait the thread to not cause a new thread until this queue is not full. (DELAY)? GANG SCHEDULING? + JNI (C)
//        executor.setThreadNamePrefix("AsyncThread-");
//        executor.afterPropertiesSet();
//        return executor;
//    }
//}
