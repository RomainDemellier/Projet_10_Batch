package com.projets.oc.projet_10_batch.model;

public class Credentials {

	private String username;
	
	private String password;
	
	public Credentials(String email, String password) {
		// TODO Auto-generated constructor stub
		this.username = email;
		this.password = password;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
