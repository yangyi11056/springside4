package com.springx.modules.metrics.exporter;

import com.springx.modules.metrics.Counter;
import com.springx.modules.metrics.Histogram;
import com.springx.modules.metrics.Timer;

public interface MetricRegistryListener {

	void onCounterAdded(String name, Counter counter);

	void onHistogramAdded(String name, Histogram histogram);

	void onTimerAdded(String name, Timer timer);
}
