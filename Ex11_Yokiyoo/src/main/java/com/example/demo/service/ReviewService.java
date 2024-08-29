package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.ReviewDTO;

public interface ReviewService {
	void insert(ReviewDTO vo);
	List<ReviewDTO> select(String name);
}
