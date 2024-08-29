package com.example.demo.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.MenuDTO;
import com.example.demo.dto.PayDTO;

@Mapper
public interface PayDAO {
	void insert(PayDTO vo)throws SQLException;
	List<PayDTO> selectOrderedMenu(int idx)throws SQLException;
	List<MenuDTO> selectMenuByUser(String login_id)throws SQLException;
}
