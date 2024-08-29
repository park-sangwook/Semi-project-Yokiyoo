package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.PayDTO;
import com.example.demo.service.PayService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PayController {
	private final PayService payService;
	@PostMapping(value = "/payOk")
	public String payOk(@RequestParam(required = false) List<String> order_name,@RequestParam(required = false) List<Integer> order_count,HttpSession session) {
		Object obj = session.getAttribute("id");
		for(int i=0;i<order_name.size();i++) {
			payService.insert(new PayDTO(order_count.get(i),order_name.get(i),obj!=null?obj.toString():""));			
		}
		return "redirect:/";
	}
	
	
}
