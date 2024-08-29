package com.example.demo.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.CompanyDTO;

@Mapper
public interface CompanyDAO {
	List<CompanyDTO> select(int idx)throws SQLException;
	CompanyDTO selectByLoginId(String login_id)throws SQLException;
}
