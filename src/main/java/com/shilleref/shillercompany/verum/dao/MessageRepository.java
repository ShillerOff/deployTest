package com.shilleref.shillercompany.verum.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.shilleref.shillercompany.verum.entity.Message;

public interface MessageRepository extends CrudRepository<Message, Long>{

	public List<Message>findByTag(String tag);
}
