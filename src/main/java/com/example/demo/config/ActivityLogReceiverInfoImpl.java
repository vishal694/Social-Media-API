package com.example.demo.config;

import com.github.rbaul.spring.boot.activity_log.ActivityLogReceiver;
import com.github.rbaul.spring.boot.activity_log.objects.ActivityLogObject;

public class ActivityLogReceiverInfoImpl implements ActivityLogReceiver {

	@Override
	public void receive(ActivityLogObject activityLogObject) {
		//log.info(">>> {}", activityLogObject);
	}

}
