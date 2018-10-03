package com.example.Service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.example.Repository.MessageRepository;
import com.example.Service.MessageService;
import com.example.model.Messages;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	MessageRepository messageRepository;
	
	@Override
	public Messages saveMessage(String message) {
		Messages mes = new Messages();
		mes.setMessage(message);
		mes.setCreatedDate(new Date());
		return 	messageRepository.save(mes);
	}
	
	@Override
	public Messages updateMessage(Messages message) {
		Messages messages = null;
		if(Objects.isNull(message)) {
			throw new Error("Please provide any data");
		}
		Optional<Messages> oppMessage = messageRepository.findById(message.getId());
		if(oppMessage.isPresent()) {
			messages = oppMessage.get();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(messages.getCreatedDate());
			calendar.add(Calendar.SECOND, 10);
			if(calendar.getTime().before(new Date())){
				messageRepository.updateMessage(message.getMessage(),new Date(),message.getId());
			}
		}
		return messageRepository.findById(message.getId()).get();
	}
	
	@Override
	public String deleteMessage(Messages message) {
		Messages messages = null;
		if(Objects.isNull(message)) {
			throw new Error("Please provide any data");
		}
		Optional<Messages> oppMessage = messageRepository.findById(message.getId());
		if(oppMessage.isPresent()) {
			messages = oppMessage.get();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(messages.getCreatedDate());
			calendar.add(Calendar.MINUTE, 2);
			if(calendar.before(new Date())) {
				messageRepository.delete(message);
			}
			
			if(ObjectUtils.isEmpty(messageRepository.findById(message.getId()).get())) {
				return "Deleted";
			}
		}
		return "2 mins not done";
	}
	
	@Override
	public List<Messages> getAllMessages(){
		return messageRepository.findAll();
	}
	
	@Override
	public List<Messages> getLimitedMessages(Pageable pageable) {
		return messageRepository.findAll(pageable).getContent();
	}

}
