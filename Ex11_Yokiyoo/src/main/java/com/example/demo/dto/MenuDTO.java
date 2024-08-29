package com.example.demo.dto;

import lombok.Data;

@Data
public class MenuDTO {
	private int menu_idx;
	private int company_idx;
	private String menu_name;
	private int menu_price;
	private String menu_image;
}
