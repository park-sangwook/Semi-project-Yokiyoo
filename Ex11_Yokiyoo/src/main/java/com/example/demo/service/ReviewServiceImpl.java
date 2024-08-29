package com.example.demo.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.ReviewDAO;
import com.example.demo.dto.ReviewDTO;

import lombok.RequiredArgsConstructor;

@Service("reviewService")
@RequiredArgsConstructor
@Transactional
public class ReviewServiceImpl implements ReviewService{
	private final ReviewDAO reviewDAO;

	@Override
	public void insert(ReviewDTO vo) {
		try {
			reviewDAO.insert(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<ReviewDTO> select(String name) {
		List<ReviewDTO> li = null;
		try {
			li = reviewDAO.select(name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return li;
	}
}
