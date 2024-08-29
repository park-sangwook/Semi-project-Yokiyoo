package com.example.demo.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ReviewDTO {
	private int review_idx;
	private int company_idx;
	private int menu_idx;
	private String review_content;
	private int review_rate;
	private Date review_date;
	private String login_id;
	private String review_image;
}
