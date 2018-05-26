package com.storage.model;

public class Worker {
	private String w_id;
	private String w_username;
	private String w_password;
	private String w_truename;
	private String w_sex;
	private String w_birth;
	public Worker(String w_id, String w_username, String w_truename,
			String w_sex, String w_birth, String w_tel) {
		super();
		this.w_id = w_id;
		this.w_username = w_username;
		this.w_truename = w_truename;
		this.w_sex = w_sex;
		this.w_birth = w_birth;
		this.w_tel = w_tel;
	}
	private String w_tel;

	
	public Worker(){}
	
	public Worker(String w_username,String w_password){
		this.w_username = w_username;
		this.w_password = w_password;
	}
	
	public String getW_id() {
		return w_id;
	}
	public void setW_id(String w_id) {
		this.w_id = w_id;
	}
	public String getW_username() {
		return w_username;
	}
	public void setW_username(String w_username) {
		this.w_username = w_username;
	}
	public String getW_password() {
		return w_password;
	}
	public void setW_password(String w_password) {
		this.w_password = w_password;
	}
	public String getW_truename() {
		return w_truename;
	}
	public void setW_truename(String w_truename) {
		this.w_truename = w_truename;
	}
	public String getW_sex() {
		return w_sex;
	}
	public void setW_sex(String w_sex) {
		this.w_sex = w_sex;
	}
	public String getW_birth() {
		return w_birth;
	}
	public void setW_birth(String w_birth) {
		this.w_birth = w_birth;
	}
	public String getW_tel() {
		return w_tel;
	}
	public void setW_tel(String w_tel) {
		this.w_tel = w_tel;
	}
}
