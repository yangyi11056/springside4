/*******************************************************************************
 * Copyright (c) 2005, 2014 springx.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.springx.examples.showcase.functional.ajax;

import static org.assertj.core.api.Assertions.*;

import com.springx.examples.showcase.functional.BaseSeleniumTestCase;
import org.junit.Test;
import org.openqa.selenium.By;

/**
 * 测试Ajax Mashup.
 * 
 * @calvin
 */
public class AjaxFT extends BaseSeleniumTestCase {

	@Test
	public void mashup() {
		s.open("/");
		s.click(By.linkText("Web演示"));
		s.click(By.linkText("跨域名Mashup演示"));

		s.click(By.xpath("//input[@value='获取内容']"));
		s.waitForVisible(By.id("mashupContent"));
		assertThat(s.getText(By.id("mashupContent"))).isEqualTo("你好，世界！");
	}
}
