/*******************************************************************************
 * Copyright (c) 2005, 2014 springx.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.springx.examples.showcase.repository.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.springx.examples.showcase.entity.Role;

public interface RoleDao extends PagingAndSortingRepository<Role, Long> {

}
