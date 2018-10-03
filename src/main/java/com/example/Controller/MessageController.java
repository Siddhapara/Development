package com.example.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Repository.MessageRepository;
import com.example.Service.MessageService;
import com.example.model.Messages;


@RestController
@RequestMapping("/message")
public class MessageController {
	
	@Autowired
	MessageService messageService;
	
	@Autowired
	MessageRepository messageRepository;
	
	@PostMapping("/save")
	public ResponseEntity<Messages> saveMessage(@RequestBody String message){
		return ResponseEntity.ok(messageService.saveMessage(message));
	}
	
	@PostMapping("/update")
	public ResponseEntity<Messages> updateMessage(@RequestBody Messages messages){
		return ResponseEntity.ok(messageService.updateMessage(messages));
	}

	@PostMapping("/delete")
	public  ResponseEntity<String> deleteMessage(@RequestBody Messages messages){
		return ResponseEntity.ok(messageService.deleteMessage(messages));
	}

	@GetMapping("/findAll")
	public  ResponseEntity<List<Messages>> findAll(){
		return ResponseEntity.ok(messageService.getAllMessages());
	}

	@GetMapping("/findXmessages")
	public  ResponseEntity<List<Messages>> findLimited(Pageable pageble){
		return ResponseEntity.ok(messageService.getLimitedMessages(pageble));
	}
}
