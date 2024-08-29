package com.example.demo.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.MenuDTO;

@Mapper
public interface MenuDAO {
	List<MenuDTO> selectByName(String name)throws SQLException;
	MenuDTO selectMenuName(String name)throws SQLException;
	List<MenuDTO> selectMenuByLoginId(String login_id)throws SQLException;
	void updateMenu(MenuDTO vo)throws SQLException;
	void deleteMenu(String name)throws SQLException;
	void insertMenu(HashMap<String, Object> map)throws SQLException;
}
