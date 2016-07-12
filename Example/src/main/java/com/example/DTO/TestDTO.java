package com.example.DTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.example.model.Member;


// update sql : insert into test(user_id, user_pw, user_name) values (?, ?, ?)
// select sql : SELECT * FROM test WHERE user_id = ?

@Component
public class TestDTO {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	
	public List<Map<String, Object>> test () {
		String sql = "select * from test";
		
		String a = "a";
		String b = "b";
		String c = "c";
		
		Object[] params = {
				a,
				b,
				c
		};
		try {
			return jdbcTemplate.queryForList(sql /* , params */);
		} catch (DataAccessException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return new ArrayList<Map<String,Object>>();
	}
	
	// start
	public int test1 ( String userId, String userPw, String userName ) {
		String sql = "insert into test(user_id, user_pw, user_name) values (?, ?, ?)";
		
		Object[] param = {
				userId,
				userPw,
				userName
		};
		try {
			return jdbcTemplate.update(sql, param);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	// public Member methodName ( String user_id )
	
	public Member get(String id){
		String sql = "SELECT * FROM test WHERE user_id = ?";
		Object[] params = {
				id
		};
		
		try {
			return jdbcTemplate.queryForObject(sql, params, new RowMapper<Member>() {
				public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
					Member member = new Member();
					member.setId(rs.getString("user_id"));
					member.setName(rs.getString("user_name"));
					member.setPw(rs.getString("user_pw"));
					return member;
				}
			});
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// return null;

}


