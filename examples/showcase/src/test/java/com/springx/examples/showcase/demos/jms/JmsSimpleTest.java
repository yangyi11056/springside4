/*******************************************************************************
 * Copyright (c) 2005, 2014 springx.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.springx.examples.showcase.demos.jms;

import static org.assertj.core.api.Assertions.*;

import com.springx.examples.showcase.demos.jms.simple.NotifyMessageProducer;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import com.springx.examples.showcase.demos.jms.simple.NotifyMessageListener;
import com.springx.examples.showcase.entity.User;
import com.springx.modules.test.category.UnStable;
import com.springx.modules.test.log.LogbackListAppender;
import com.springx.modules.test.spring.SpringContextTestCase;
import com.springx.modules.utils.Threads;

@Category(UnStable.class)
@DirtiesContext
@ContextConfiguration(locations = { "/applicationContext.xml", "/jms/applicationContext-jms-simple.xml" })
public class JmsSimpleTest extends SpringContextTestCase {

	@Autowired
	private NotifyMessageProducer notifyMessageProducer;

	@Test
	public void queueMessage() {
		Threads.sleep(1000);
		LogbackListAppender appender = new LogbackListAppender();
		appender.addToLogger(NotifyMessageListener.class);

		User user = new User();
		user.setName("calvin");
		user.setEmail("calvin@sringside.org.cn");

		notifyMessageProducer.sendQueue(user);
		logger.info("sended message");

		Threads.sleep(1000);
		assertThat(appender.getFirstMessage()).isEqualTo("UserName:calvin, Email:calvin@sringside.org.cn");
	}

	@Test
	public void topicMessage() {
		Threads.sleep(1000);
		LogbackListAppender appender = new LogbackListAppender();
		appender.addToLogger(NotifyMessageListener.class);

		User user = new User();
		user.setName("calvin");
		user.setEmail("calvin@sringside.org.cn");

		notifyMessageProducer.sendTopic(user);
		logger.info("sended message");

		Threads.sleep(1000);
		assertThat(appender.getFirstMessage()).isEqualTo("UserName:calvin, Email:calvin@sringside.org.cn");
	}
}
