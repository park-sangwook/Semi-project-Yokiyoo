package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.MenuDTO;
import com.example.demo.dto.PayDTO;

public interface PayService {
	void insert(PayDTO vo);
	List<PayDTO> selectOrderedMenu(int idx);
	List<MenuDTO> selectMenuByUser(String login_id);
}
