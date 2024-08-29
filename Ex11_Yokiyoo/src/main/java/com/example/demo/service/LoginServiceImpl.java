package com.example.demo.service;

import java.sql.SQLException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.LoginDAO;
import com.example.demo.dto.LoginDTO;

import lombok.RequiredArgsConstructor;

@Service("loginService")
@RequiredArgsConstructor
@Transactional
public class LoginServiceImpl implements LoginService{
	private final LoginDAO loginDAO;

	@Override
	public LoginDTO select(String login_name) {
		LoginDTO vo = null;
		try {
			vo = loginDAO.select(login_name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo;
	}

	@Override
	public void updatePassword(LoginDTO vo) {
		try {
			loginDAO.updatePassword(vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
