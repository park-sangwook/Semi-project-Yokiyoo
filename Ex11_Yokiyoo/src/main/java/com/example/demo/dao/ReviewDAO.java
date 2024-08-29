package com.example.demo.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.ReviewDTO;

@Mapper
public interface ReviewDAO {
	void insert(ReviewDTO vo)throws SQLException;
	List<ReviewDTO> select(String name)throws SQLException;
}
