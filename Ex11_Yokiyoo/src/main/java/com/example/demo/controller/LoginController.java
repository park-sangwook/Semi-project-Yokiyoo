package com.example.demo.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.CompanyDTO;
import com.example.demo.dto.LoginDTO;
import com.example.demo.service.CompanyService;
import com.example.demo.service.LoginService;
import com.example.demo.service.MenuService;
import com.example.demo.service.PayService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;import jakarta.websocket.Session;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {
	private final LoginService loginService;
	private final MenuService menuService;
	private final CompanyService companyService;
	private final PayService payService;
	@PostMapping(value = "/loginOk")
	public String loginOk(LoginDTO vo,RedirectAttributes rt,@RequestParam(required = false) boolean chk,HttpServletResponse response,HttpSession session) {
		LoginDTO password = loginService.select(vo.getLogin_id());
		if(password!=null && password.getLogin_password().equals(vo.getLogin_password())) {
			if(chk) {
				Cookie cookie = new Cookie("id", vo.getLogin_id());
				cookie.setMaxAge(6*60*60);
				response.addCookie(cookie);
			}else {
				Cookie cookie = new Cookie("id", vo.getLogin_id());
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
			session.setAttribute("id", vo.getLogin_id());
			session.setAttribute("code", password.getLogin_code());
			session.setMaxInactiveInterval(2*60*60);
			return "redirect:/";			
		}
		rt.addFlashAttribute("msg","아이디 혹은 비밀번호가 다릅니다.");
		if(vo.getLogin_id().trim().length()==0)rt.addFlashAttribute("msg","아이디를 입력해주세요.");
		else if(vo.getLogin_password().trim().length()==0)rt.addFlashAttribute("msg","비밀번호를 입력해주세요.");
		return "redirect:/login";			
	}
	@GetMapping(value = "/logout")
	public String logoutOk(HttpSession session) {
		session.removeAttribute("id");
		if(session.getAttribute("code")!=null)session.removeAttribute("code");
		return "redirect:/";
	}
	@GetMapping(value = "/mypage")
	public String mypage(HttpSession session,Model model) {
		String login_id = session.getAttribute("id").toString();
		if(session.getAttribute("code")!=null && session.getAttribute("code").toString().equals("2")) {
			model.addAttribute("menu",menuService.selectMenuByLoginId(login_id));
			CompanyDTO company = companyService.selectByLoginId(login_id);
			model.addAttribute("company",company);
			model.addAttribute("order",payService.selectOrderedMenu(company.getCompany_idx()));
			return "companyMyPage";
		}
		//model.addAttribute("order",payService.selectMenuByUser(login_id));
		model.addAttribute("user",loginService.select(login_id));
		return "userMyPage";
	}
	@PostMapping(value = "/kakaoLoginOk")
	public String kakaoLoginOk(@RequestBody HashMap<String, Object> map,HttpSession session) {
		session.setAttribute("id", map.get("id"));
		session.setMaxInactiveInterval(2*60*60);
		return "redirect:/";
	}
	@GetMapping(value = "/findPassword")
	public String findPassword() {
		return "findPassword";
	}
}
