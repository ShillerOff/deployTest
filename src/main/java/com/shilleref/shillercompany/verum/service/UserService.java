package com.shilleref.shillercompany.verum.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.shilleref.shillercompany.verum.dao.UserRepo;
import com.shilleref.shillercompany.verum.entity.Role;
import com.shilleref.shillercompany.verum.entity.User;

@Service
public class UserService implements UserDetailsService{
	
	@Value("${approve.link}")
	private String linkToApprove;
	
	@Autowired(required = true)
	private UserRepo userRepo;
	
	@Autowired
	private SmtpMailSender mailSender;
	
	@Autowired
	PasswordEncoder passwrodEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		
		return user;
	}
	
	public boolean AddUser(User user) {
		User userFromDb = userRepo.findByUsername(user.getUsername());
		
		if(userFromDb != null) {
			return false;
		}
		
		user.setActive(true);
		user.setRoles(Collections.singleton(Role.USER));
		user.setActivasionCode(UUID.randomUUID().toString());
//		//Шифровка паролей
//		user.setPassword(passwrodEncoder.encode(user.getPassword()));
		
		userRepo.save(user);
		
		sendMessage(user);
		
		return true;
	}

	private void sendMessage(User user) {
		if(!StringUtils.isEmpty(user.getEmail())) {
			String message = String.format(
					"Hello, %s! \n" +
                            "Welcome to Verum. Please, visit next link: " + linkToApprove
							, user.getUsername()
							, user.getActivasionCode());
			
			
			mailSender.send(user.getEmail(), "Activation code", message);
		}
	}

	public boolean activateUser(String code) {
		User user = userRepo.findByActivationCode(code);
		
		if(user == null) {
			return false;
		}
		
		user.setActivasionCode(null);
		
		userRepo.save(user);
		
		return true;
	}

	public List<User> findAll() {
		return userRepo.findAll();
	}

	public void saveUser(User user, String username, Map<String, String> form) {
		user.setUsername(username);
		
		Set<String> roles = Arrays.stream(Role.values())
				.map(Role::name)
				.collect(Collectors.toSet());
		
		user.getRoles().clear();
		
		for (String key : form.keySet()) {
			if(roles.contains(key)) {
				user.getRoles().add(Role.valueOf(key));
			}
		}
		
		userRepo.save(user);
		
	}

	public void updateProfile(User user, String newPassword, String newEmail) {
		String email = user.getEmail();
		
		boolean isEmailChanged = (email != null && !email.equals(newEmail)) ||
				(newEmail != null && newEmail.equals(email));
		
		if(isEmailChanged) {
			user.setEmail(newEmail);
			
			if(!StringUtils.isEmpty(email)) {
				user.setActivasionCode(UUID.randomUUID().toString());
			}
		}
		
		if(!StringUtils.isEmpty(newPassword)) {
			user.setPassword(newPassword);
		}
		
		userRepo.save(user);
		
		if(isEmailChanged) {
			sendMessage(user);	
		}
		
	}
}
