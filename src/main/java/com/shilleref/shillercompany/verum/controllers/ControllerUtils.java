package com.shilleref.shillercompany.verum.controllers;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.shilleref.shillercompany.verum.entity.Message;

public class ControllerUtils {
    static Map<String, String> getErrors(BindingResult bindingResult) {
        Collector<FieldError, ?, Map<String, String>> collector = Collectors.toMap(
                fieldError -> fieldError.getField() + "Error",
                FieldError::getDefaultMessage
        );
        return bindingResult.getFieldErrors().stream().collect(collector);
    }
    
//    public static void saveFile(
//    		@Valid Message message,
//    		@RequestParam("file") MultipartFile file,
//    		String uploadPath
//    		)
//    		throws IllegalStateException, IOException {
//    	
//		if(file != null && !file.getOriginalFilename().isEmpty()) {
//			File uploadDir = new File(uploadPath);
//
//			if(!uploadDir.exists()) {
//				uploadDir.mkdir();
//			}
//
//			String uuidFile = UUID.randomUUID().toString();
//			String resultFilename = uuidFile + "." + file.getOriginalFilename();
//
//			file.transferTo(new File(uploadPath + "/" + resultFilename));
//
//			message.setFilename(resultFilename);
//		}
//    }
}
