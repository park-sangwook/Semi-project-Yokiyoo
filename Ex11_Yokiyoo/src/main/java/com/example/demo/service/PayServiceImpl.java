package com.example.demo.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.PayDAO;
import com.example.demo.dto.MenuDTO;
import com.example.demo.dto.PayDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service("payService")
@RequiredArgsConstructor
@Slf4j
@Transactional
public class PayServiceImpl implements PayService{
	private final PayDAO payDAO;

	@Override
	public void insert(PayDTO vo) {
		log.info("결과 : {}",vo);
		try {
			payDAO.insert(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<PayDTO> selectOrderedMenu(int idx) {
		List<PayDTO> li = null;
		try {
			li = payDAO.selectOrderedMenu(idx);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}

	@Override
	public List<MenuDTO> selectMenuByUser(String login_id) {
		List<MenuDTO> li = null;
		try {
			li = payDAO.selectMenuByUser(login_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}
}
