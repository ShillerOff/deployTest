package com.shilleref.shillercompany.verum.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shilleref.shillercompany.verum.entity.User;

public interface UserRepo extends JpaRepository<User, Long>{
	User findByUsername(String username);

	User findByActivationCode(String code);
}
