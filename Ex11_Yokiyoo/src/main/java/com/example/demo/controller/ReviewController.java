package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.ReviewDTO;
import com.example.demo.service.PayService;
import com.example.demo.service.ReviewService;

import io.github.classgraph.Resource;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ReviewController {
	private final ReviewService reviewService;
	private final PayService payService;
	private final ResourceLoader loader;
	@GetMapping(value = "/review/{idx}")
	public String review(@PathVariable int idx,Model model) {
		model.addAttribute("menu_idx",idx);
		return "review";
	}
	@PostMapping(value = "/reviewInsertOk")
	public String reviewInsertOk(HttpSession session,ReviewDTO vo,@RequestParam(required = false) MultipartFile menu_image) {
		String loginId = session.getAttribute("id").toString();
		vo.setLogin_id(loginId);
		try {
			String path = loader.getResource("file:/D:/SpringImages/").getURI().toString()+"review_images/";
			path = path.substring(6);
			File file = new File(path);
			if(!file.exists())file.mkdirs();
//			menu_image.transferTo(new File(path+menu_image.getOriginalFilename()));
			FileCopyUtils.copy(menu_image.getBytes(), new File(path+menu_image.getOriginalFilename()));
			vo.setReview_image(menu_image.getOriginalFilename());
			log.info("파일 경로 : {} {}",path+menu_image.getOriginalFilename(),vo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reviewService.insert(vo);
		return "redirect:/mypage";
	}
	
	@GetMapping(value = "/review-insert")
	public String reviewInsert(Model model,HttpSession session) {
		model.addAttribute("order",payService.selectMenuByUser(session.getAttribute("id").toString()));
		return "reviewInsert";
	}
}
