package com.example.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DTO.TestDTO;
import com.example.model.Member;

@Service
public class TestService {
	@Autowired
	TestDTO testDTO;
	
	public List<Map<String, Object>> test () {
		return testDTO.test();
	}
	public int test1 ( String userId, String userPw, String userName ) {
		return testDTO.test1(userId, userPw, userName);
	}
	
	public Member get(String id) {
		return testDTO.get(id);
	}
}
