package com.shilleref.shillercompany.verum.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.shilleref.shillercompany.verum.entity.Message;

@Service
public interface MessageRepository extends CrudRepository<Message, Long>{

	public List<Message>findByTag(String tag);
}
