package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.CompanyDTO;

public interface CompanyService {
	List<CompanyDTO> select(int idx);
	CompanyDTO selectByLoginId(String login_id);
}
