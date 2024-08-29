package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.MenuDTO;
import com.example.demo.dto.PayDTO;
import com.example.demo.service.LoginService;
import com.example.demo.service.MenuService;
import com.example.demo.service.PayService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MyPageController {
	private final MenuService menuService;
	private final LoginService loginService;
	private final PayService payService;
	@PostMapping(value = "/menuUpdateOk")
	public String menuUpdateOk(MenuDTO vo) {
		menuService.updateMenu(vo);
		return "redirect:/mypage";
	}
	
	@PostMapping(value = "/menuInsertOk")
	public String menuInsertOk(@RequestParam(required = false) HashMap<String, Object> map,@RequestParam(required = false) MultipartFile menu_image) {
		map.put("menu_image", "/images/"+menu_image.getOriginalFilename());
		menuService.insertMenu(map);
		return "redirect:/mypage";
	}
	
	@DeleteMapping(value = "/menuDeleteOk/{name}")
	@ResponseBody
	public void menuDeleteOk(@PathVariable String name) {
		menuService.deleteMenu(name);
	}
	
	
	
	@GetMapping(value = "/orderedList/{idx}")
	@ResponseBody
	public List<PayDTO> orderedList(@PathVariable int idx){
		return payService.selectOrderedMenu(idx);
	}
	
	@PostMapping(value = "/userUpdateOk")
	public String userUpdateOk(LoginDTO vo) {
		log.info("결과 : {}",vo);
		loginService.updatePassword(vo);
		return "redirect:/";
	}
}
