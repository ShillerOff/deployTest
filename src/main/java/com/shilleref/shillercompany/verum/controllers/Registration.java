package com.shilleref.shillercompany.verum.controllers;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.shilleref.shillercompany.verum.entity.Role;
import com.shilleref.shillercompany.verum.dao.UserRepo;
import com.shilleref.shillercompany.verum.entity.User;
import com.shilleref.shillercompany.verum.entity.dto.CaptchaResponse;
import com.shilleref.shillercompany.verum.service.UserService;

@Controller
public class Registration {
	private static String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";
	
	
	@Autowired
	private UserService userService;
	
	@Value("${recaptcha.secret}")
	private String secret;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	@GetMapping("/registration")
	public String registration() {
		return "registration";
	}
	
	@PostMapping("/registration")
	public String addUser(
			@RequestParam("password2") 
			String passwordConfirmation,
			@RequestParam("g-recaptcha-response")
			String captchaResponse,
			@Valid User user,
			BindingResult bindingResult,
			Model model
			){
		
		String url = String.format(CAPTCHA_URL, secret, captchaResponse);
		CaptchaResponse response= restTemplate.postForObject(url, Collections.emptyList(), CaptchaResponse.class);
		
		if(!response.isSuccess()) {
			model.addAttribute("captchaError", "Fill captcha");
//			model.addAttribute("captchaError", response.getErrorCode().toString());
		}
		
		boolean isConfirmEmpty = StringUtils.isEmpty(passwordConfirmation);
		
		if(isConfirmEmpty) {
			model.addAttribute("password2Error","Password confirmation canot be empty");
		}
		
		if(user.getPassword() != null && !user.getPassword().equals(passwordConfirmation)) {
			model.addAttribute("passwordError", "Passwords are different");
		}
		
		if(isConfirmEmpty || bindingResult.hasErrors() || !response.isSuccess()) {
			Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
			
			model.mergeAttributes(errors);
			
			System.out.println(errors);
			
			return "registration";
		}
		
		if(!userService.AddUser(user)) {
			model.addAttribute("usernameEror", "User already exists!");
			return "registration";
		}
		
		return "redirect:/login";
	}
	
	@GetMapping("/activate/{code}")
	public String activate(Model model, @PathVariable String code) {

		boolean isActiv = userService.activateUser(code);	

		if(isActiv) {
			model.addAttribute("messageType", "success");
			model.addAttribute("message", "successfully activated");
		}else {
			model.addAttribute("messageType", "danger");
			model.addAttribute("message", "Activation code is not found!");
		}

		return "login";

	}
}
