package com.example.Service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.example.model.Messages;


public interface MessageService{

	Messages saveMessage(String message);

	Messages updateMessage(Messages message);

	String deleteMessage(Messages message);

	List<Messages> getAllMessages();

	List<Messages> getLimitedMessages(Pageable pageble);

}
