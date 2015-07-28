/*******************************************************************************
 * Copyright (c) 2005, 2014 springx.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.springx.examples.showcase.webservice.soap.response;

import javax.xml.bind.annotation.XmlType;

import com.springx.examples.showcase.webservice.soap.WsConstants;
import com.springx.examples.showcase.webservice.soap.response.base.WSResult;
import com.springx.examples.showcase.webservice.soap.response.dto.UserDTO;

@XmlType(name = "GetUserResult", namespace = WsConstants.NS)
public class GetUserResult extends WSResult {
	private UserDTO user;

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}
}
