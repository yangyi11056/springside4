/*******************************************************************************
 * Copyright (c) 2005, 2014 springx.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.springx.modules.metrics;

public class TimerMetric {
	public CounterMetric counterMetric;
	public HistogramMetric histogramMetric;

	@Override
	public String toString() {
		return "IimerMetric [counterMetric=" + counterMetric + ", histogramMetric=" + histogramMetric + "]";
	}
}
