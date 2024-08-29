package com.example.demo.dto;

import lombok.Data;

@Data
public class LoginDTO {
	private int login_idx;
	private String login_id;
	private String login_password;
	private int login_code;
}
