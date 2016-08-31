/**
 * 
 */
package com.ymt.mirage.poster.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author zhailiang
 * @since 2016年5月6日
 */
@Configuration
public class PosterConfig {
	
	@Bean
	public TaskExecutor posterTaskExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setCorePoolSize(100);
		taskExecutor.afterPropertiesSet();
		return taskExecutor;
	}

}
