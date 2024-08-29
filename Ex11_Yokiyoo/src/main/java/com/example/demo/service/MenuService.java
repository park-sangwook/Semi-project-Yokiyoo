package com.example.demo.service;

import java.util.HashMap;
import java.util.List;

import com.example.demo.dto.MenuDTO;

public interface MenuService {
	List<MenuDTO> selectByName(String name);
	MenuDTO selectMenuName(String name);
	List<MenuDTO> selectMenuByLoginId(String login_id);
	void updateMenu(MenuDTO vo);
	void deleteMenu(String name);
	void insertMenu(HashMap<String, Object> map);
}
