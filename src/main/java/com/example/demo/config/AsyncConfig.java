package com.example.demo.config;

import java.util.concurrent.Executor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class AsyncConfig extends AsyncConfigurerSupport {
	
	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
		threadPoolTaskExecutor.setCorePoolSize(5);
		threadPoolTaskExecutor.setMaxPoolSize(10);
		threadPoolTaskExecutor.setQueueCapacity(500);
		threadPoolTaskExecutor.setThreadNamePrefix("userThread-");
		threadPoolTaskExecutor.initialize();
		return super.getAsyncExecutor();
	}

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		// TODO Auto-generated method stub
		return super.getAsyncUncaughtExceptionHandler();
	}

}
