package com.tir.ocinio;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tir.ocinio.util.EmailUtils;

@SpringBootTest
public class MailSendingTest {

	@Autowired
	private EmailUtils mail;
	
	@Test
	public void sendEmail() {
		
		mail.sendEmail("marcovalerio71299@gmail.com", "Test Email", "Hello World!");
		
	}
	
}
