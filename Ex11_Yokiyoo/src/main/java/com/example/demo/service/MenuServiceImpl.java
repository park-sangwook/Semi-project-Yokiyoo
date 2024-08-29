package com.example.demo.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.MenuDAO;
import com.example.demo.dto.MenuDTO;

import lombok.RequiredArgsConstructor;

@Service("menuServiceImpl")
@RequiredArgsConstructor
@Transactional
public class MenuServiceImpl implements MenuService{
	private final MenuDAO menuDAO;
	@Override
	public List<MenuDTO> selectByName(String name) {
		List<MenuDTO> li = null;
		try {
			li = menuDAO.selectByName(name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return li;
	}
	@Override
	public MenuDTO selectMenuName(String name) {
		MenuDTO vo = null;
		try {
			vo = menuDAO.selectMenuName(name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo;
	}
	@Override
	public List<MenuDTO> selectMenuByLoginId(String login_id) {
		List<MenuDTO> li = null;
		try {
			li = menuDAO.selectMenuByLoginId(login_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}
	@Override
	public void updateMenu(MenuDTO vo) {
		try {
			menuDAO.updateMenu(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void insertMenu(HashMap<String, Object> map) {
		try {
//			HashMap<String, Object> map = new HashMap<>();
//			map.put("company_name", company_name);
//			map.put("menu_name", menu_name);
//			map.put("menu_price", menu_price);
//			map.put("menu_image", menu_image);
			menuDAO.insertMenu(map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void deleteMenu(String name) {
		try {
			menuDAO.deleteMenu(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
