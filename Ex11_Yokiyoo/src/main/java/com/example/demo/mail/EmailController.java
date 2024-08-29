package com.example.demo.mail;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class EmailController {
	private final EmailService emailService;
	@PostMapping("/reset-password")
	public String resetPassword(@RequestParam String email) {
		// 이메일로 임시 비밀번호 전송
		emailService.sendTemporaryPassword(email);
		return "redirect:/login";
	}
}
