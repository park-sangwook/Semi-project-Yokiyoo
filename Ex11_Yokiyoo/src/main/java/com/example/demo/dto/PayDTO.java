package com.example.demo.dto;

import lombok.Data;

@Data
public class PayDTO {
	private int pay_idx;
	private int menu_idx;
	private String login_id;
	private int pay_count;	
	private String menu_name;
	private int company_idx;
	public PayDTO(int pay_count, String order_name,String login_id) {
		super();
		this.pay_count = pay_count;
		this.menu_name = order_name;
		this.login_id=login_id;
	}
}
