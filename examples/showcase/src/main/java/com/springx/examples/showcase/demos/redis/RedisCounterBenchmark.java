/*******************************************************************************
 * Copyright (c) 2005, 2014 springx.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.springx.examples.showcase.demos.redis;

import com.springx.modules.nosql.redis.JedisTemplate;
import com.springx.modules.nosql.redis.pool.JedisPool;
import com.springx.modules.nosql.redis.pool.JedisPoolBuilder;
import com.springx.modules.test.benchmark.BenchmarkTask;
import com.springx.modules.test.benchmark.ConcurrentBenchmark;

/**
 * 测试Redis用于计数器时incr()方法的性能.
 * 
 * 可用-Dthread.count, -Dtotal.count 重置测试规模
 * 可用-Dredis.host,-Dredis.port,-Dredis.timeout 重置连接参数
 * 
 * @author calvin
 */
public class RedisCounterBenchmark extends ConcurrentBenchmark {
	private static final int DEFAULT_THREAD_COUNT = 20;
	private static final long DEFAULT_TOTAL_COUNT = 100000;

	private String counterKey = "ss.counter";
	private JedisPool pool;
	private JedisTemplate jedisTemplate;

	public static void main(String[] args) throws Exception {
		RedisCounterBenchmark benchmark = new RedisCounterBenchmark();
		benchmark.execute();
	}

	public RedisCounterBenchmark() {
		super(DEFAULT_THREAD_COUNT, DEFAULT_TOTAL_COUNT);
	}

	@Override
	protected void setUp() {

		pool = new JedisPoolBuilder().setDirectHostAndPort("localhost", "6379").setPoolSize(threadCount).buildPool();
		jedisTemplate = new JedisTemplate(pool);

		// 重置Counter
		jedisTemplate.set(counterKey, "0");
	}

	@Override
	protected void tearDown() {
		pool.destroy();
	}

	@Override
	protected BenchmarkTask createTask() {
		return new CounterTask();
	}

	public class CounterTask extends BenchmarkTask {

		@Override
		protected void execute(int requestSequence) {
			jedisTemplate.incr(counterKey);
		}
	}
}
