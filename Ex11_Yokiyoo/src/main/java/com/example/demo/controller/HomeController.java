package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.MenuDTO;
import com.example.demo.service.CompanyService;
import com.example.demo.service.MenuService;
import com.example.demo.service.ReviewService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {
	private final CompanyService companyService;
	private final MenuService menuService;
	private final ReviewService reviewService;
	@GetMapping(value = "/")
	public String index() {
		return "index";
	}
	@GetMapping(value = "/company/{idx}")
	public String company(@PathVariable int idx,Model model) {
		model.addAttribute("vo",companyService.select(idx));
		return "company";
	}
	
	@GetMapping(value = "/company/{idx}/{name}")
	public String companyName(@PathVariable int idx,@PathVariable String name,Model model) {
		model.addAttribute("vo",menuService.selectByName(name));
		model.addAttribute("name",name);
		model.addAttribute("menu",menuService.selectByName(name));
		model.addAttribute("review",reviewService.select(name));
		return "companyByName";
	}
	@GetMapping(value = "/login")
	public String login(HttpServletRequest request,Model model) {
		Cookie[] cookies = request.getCookies();
		if(!Objects.isNull(cookies)) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("id")) {
					model.addAttribute("id",cookie.getValue());
					break;
				}
			}
			
		}
		return "login";
	}
	@GetMapping(value = "/example")
	public String example() {
		return "example";
	}
	
	@GetMapping(value = "/selectMenuName/{name}")
	@ResponseBody
	public MenuDTO selectMenuName(@PathVariable String name) {
		return menuService.selectMenuName(name);
	}
	
	@PostMapping(value = "/pay")
	public String pay(@RequestParam(required = false)List<String> order_name,@RequestParam(required = false)List<Integer> order_price,@RequestParam(required = false) List<Integer> order_count,@RequestParam(required = false)String company_name,Model model) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("company_name", company_name);
		map.put("order_name", order_name);
		map.put("order_price", order_price);
		int[] total = new int[1];
		order_price.forEach(item->total[0]+=item);
		model.addAttribute("map",map);
		model.addAttribute("total",total[0]);
		try {
			model.addAttribute("order_name",new ObjectMapper().writeValueAsString(order_name));
			model.addAttribute("order_count",new ObjectMapper().writeValueAsString(order_count));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "pay";
	}
}
