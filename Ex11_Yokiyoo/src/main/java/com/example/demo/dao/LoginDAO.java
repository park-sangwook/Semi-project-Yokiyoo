package com.example.demo.dao;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.LoginDTO;

@Mapper
public interface LoginDAO {
	LoginDTO select(String login_id)throws SQLException;
	void updatePassword(LoginDTO vo)throws SQLException;
}
