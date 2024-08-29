package com.example.demo.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.CompanyDAO;
import com.example.demo.dto.CompanyDTO;

import lombok.RequiredArgsConstructor;

@Service("companyService")
@RequiredArgsConstructor
@Transactional
public class CompanyServiceImpl implements CompanyService{
	private final CompanyDAO companyDAO;

	@Override
	public List<CompanyDTO> select(int idx) {
		List<CompanyDTO> li = null;
		try {
			li = companyDAO.select(idx);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return li;
	}

	@Override
	public CompanyDTO selectByLoginId(String login_id) {
		CompanyDTO li = null;
		try {
			li = companyDAO.selectByLoginId(login_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}
}
