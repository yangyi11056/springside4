/*******************************************************************************
 * Copyright (c) 2005, 2014 springx.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.springx.examples.showcase.demos.metrics;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import com.springx.modules.metrics.MetricRegistry;
import com.springx.modules.metrics.exporter.JmxExporter;
import com.springx.modules.metrics.reporter.GraphiteReporter;
import com.springx.modules.metrics.reporter.ReportScheduler;
import com.springx.modules.metrics.reporter.Slf4jReporter;

/**
 * 注册多个Reporter
 * 
 * @author Administrator
 */
public class MetricsManager {

	private ReportScheduler scheduler;

	private JmxExporter exporter;

	private boolean graphiteEnabled = false;

	@PostConstruct
	public void start() {
		Slf4jReporter slf4jReporter = new Slf4jReporter();
		scheduler = new ReportScheduler(MetricRegistry.INSTANCE, slf4jReporter);

		if (graphiteEnabled) {
			GraphiteReporter graphiteReporter = new GraphiteReporter(new InetSocketAddress("localhost", 2003));
			scheduler.addReporter(graphiteReporter);
		}

		scheduler.start(10, TimeUnit.SECONDS);

		exporter = new JmxExporter("metrics", MetricRegistry.INSTANCE);
	}

	@PreDestroy
	public void stop() {
		scheduler.stop();
	}

	public void setGraphiteEnabled(boolean graphiteEnabled) {
		this.graphiteEnabled = graphiteEnabled;
	}
}
