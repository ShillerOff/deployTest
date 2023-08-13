package com.shilleref.shillercompany.verum.controllers;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shilleref.shillercompany.verum.dao.UserRepo;
import com.shilleref.shillercompany.verum.entity.Role;
import com.shilleref.shillercompany.verum.entity.User;
import com.shilleref.shillercompany.verum.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public String userList(Model model) {
		
		model.addAttribute("users", userService.findAll());
		
		return "user-list";
	}
	@GetMapping("{user}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String userEditForm(@PathVariable User user, Model model) {
		model.addAttribute("user", user);
		model.addAttribute("roles", Role.values());
		return "user-edit";
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public String userSave(@RequestParam Map<String, String> form,
						   @RequestParam("userId") User user,
						   @RequestParam String username) {
		
		userService.saveUser(user, username, form);
		
		return "redirect:/user";
	}
	
	@GetMapping("profile")
	public String getProfile(Model model, @AuthenticationPrincipal User user) {
		
		model.addAttribute("username", user.getUsername());
		model.addAttribute("email", user.getEmail());
		
		return "profile";
	}
	
	@PostMapping("profile")
	public String updateProfiel(@AuthenticationPrincipal User user,
								@RequestParam String password,
								@RequestParam String email) {
		
		userService.updateProfile(user,password,email);
		
		return "redirect:/user/profile";
	}
}
