package com.myspring.afterdish.member.vo;

import java.sql.Date;
import org.springframework.stereotype.Component;

@Component("memverVO")
public class MemberVO {
	private String id;
	private String pw;
	private String name;
	private String authority;
	private String email;
	private String call;
	private Date birth;
	private String address;
	private String gender;
	
	public void setId(String id) {
		this.id =id;
	}
	public String getId() {
		return id;
	}
	
	public void setPw(String pw) {
		this.pw =pw;
	}
	public String getPw() {
		return pw;
	}
	
	public void setName(String name) {
		this.name =name;
	}
	public String getName() {
		return name;
	}
	
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public String getAuthority() {
		return authority;
	}
	
	public void setEmail(String email) {
		this.email =email;
	}
	public String getEmail() {
		return email;
	}
	
	public void setCall(String call) {
		this.call=call;
	}
	public String getCall() {
		return call;
	}
	
	public void setGender(String gender) {
		this.gender=gender;
	}
	public String getGender() {
		return gender;
	}
	public void setAddress(String address) {
		this.address=address;
	}
	public String getAddress() {
		return address;
	}
	public void setBirth(Date birth) {
		this.birth=birth;
	}
	public Date getBirth() {
		return birth;
	}
}
