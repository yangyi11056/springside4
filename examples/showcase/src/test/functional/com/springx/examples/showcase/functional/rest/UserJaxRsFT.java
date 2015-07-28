/*******************************************************************************
 * Copyright (c) 2005, 2014 springx.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.springx.examples.showcase.functional.rest;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import com.springx.examples.showcase.functional.BaseFunctionalTestCase;
import com.springx.examples.showcase.webservice.rest.UserDTO;

/**
 * 对基于JAX-RS的实现Restful的测试
 * 
 * @author calvin
 */
public class UserJaxRsFT extends BaseFunctionalTestCase {

	private static String resourceUrl = baseUrl + "/cxf/jaxrs/user";

	private RestTemplate restTemplate = new RestTemplate();

	@Test
	public void getUser() {
		UserDTO user = restTemplate.getForObject(resourceUrl + "/{id}.xml", UserDTO.class, 1L);
		assertThat(user.getLoginName()).isEqualTo("admin");
		assertThat(user.getName()).isEqualTo("管理员");
		assertThat(user.getTeamId()).isEqualTo(1);

		try {
			user = restTemplate.getForObject(resourceUrl + "/{id}.json", UserDTO.class, 1L);
		} catch (HttpStatusCodeException e) {
			fail(e.getMessage());
		}
		assertThat(user.getLoginName()).isEqualTo("admin");
		assertThat(user.getName()).isEqualTo("管理员");
		assertThat(user.getTeamId()).isEqualTo(1);
	}
}
