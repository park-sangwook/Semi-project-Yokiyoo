package com.example.demo.service;

import com.example.demo.dto.LoginDTO;

public interface LoginService {
	LoginDTO select(String login_name);
	void updatePassword(LoginDTO vo);
}
