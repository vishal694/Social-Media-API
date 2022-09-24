package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//import com.github.rbaul.spring.boot.activity_log.config.EnableActivityLog;

@Configuration
//@EnableActivityLog
public class ActivityLogConfig {
	
	@Bean
	public ActivityLogReceiverInfoImpl activityLogReceiverInfo() {
		return new ActivityLogReceiverInfoImpl();
	}
}
