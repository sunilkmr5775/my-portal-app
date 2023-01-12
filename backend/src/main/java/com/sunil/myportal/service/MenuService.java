package com.sunil.myportal.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.sunil.myportal.model.Menu;

@Service
public interface MenuService {

	public Menu addMenu(Menu menu);

	public Set<Menu> getMenus();


	
}
