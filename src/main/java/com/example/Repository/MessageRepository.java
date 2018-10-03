package com.example.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.model.Messages;

public interface MessageRepository extends JpaRepository<Messages,Long> {
	
	@Modifying
    @Transactional
    @Query("update Messages m set m.message=?1 , m.createdDate=?2 where m.id=?3 ")
    void updateMessage(String message,Date date,Long id);
	
	Page<Messages> findAll(Pageable pageable);
}
