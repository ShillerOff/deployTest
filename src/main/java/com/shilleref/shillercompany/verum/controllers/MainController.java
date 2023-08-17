package com.shilleref.shillercompany.verum.controllers;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.shilleref.shillercompany.verum.dao.MessageRepository;
import com.shilleref.shillercompany.verum.entity.Message;
import com.shilleref.shillercompany.verum.entity.User;

@Controller
public class MainController {
	
	@Autowired(required = true)
	MessageRepository messageRepo;
	
	@Value("${upload.path}") // Spring будет искать переменную upload.path, а она хрантися в application.properties
	private String uploadPath;
	
	@GetMapping("/")
	public String greetingController(Map<String, Object> model) {
		
		return "greeting";
	}
	
	@GetMapping("/main")
	public String mainController(Model model,
								@RequestParam(required = false, defaultValue = "")
								String filter 
								) {
		
		Iterable<Message> messages = messageRepo.findAll();
		
		if(filter != null && !filter.isEmpty()) {
			messages = messageRepo.findByTag(filter);
		}else {
			messages = messageRepo.findAll();
		}
		
		model.addAttribute("messages", messages);
		model.addAttribute("filter", filter);
		
		return "main";
	}
	
	@PostMapping("/main")
	public String addMessage(
			@AuthenticationPrincipal User user,
			@Valid Message message,
			BindingResult bindingResult, //BindingResult всегда должен идти перед аргументов Model
			Model model,
			@RequestParam("file") MultipartFile file
			) throws IllegalStateException, IOException {
		
		message.setAuthor(user);
		
//		System.out.println(user);
		
		if(bindingResult.hasErrors()) {
			Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
			
			System.out.println(errorsMap);
			
            model.mergeAttributes(errorsMap);
            model.addAttribute("message", message);
			
		}else {
		
			if(file != null && !file.getOriginalFilename().isEmpty()) {
				File uploadDir = new File(uploadPath);

				if(!uploadDir.exists()) {
					uploadDir.mkdir();
				}

				String uuidFile = UUID.randomUUID().toString();
				String resultFilename = uuidFile + "." + file.getOriginalFilename();

				file.transferTo(new File(uploadPath + "/" + resultFilename));

				message.setFilename(resultFilename);
			}

			model.addAttribute("message", null);
			
			messageRepo.save(message);
		}	

		Iterable<Message> messages = messageRepo.findAll();
		
		model.addAttribute("messages", messages);
		
		return "main";
		
	}
}
