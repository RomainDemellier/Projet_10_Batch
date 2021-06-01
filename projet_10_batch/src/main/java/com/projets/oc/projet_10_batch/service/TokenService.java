package com.projets.oc.projet_10_batch.service;

import org.springframework.stereotype.Service;

@Service
public class TokenService {

	private String token = "";
	
	public String getToken() {
		return this.token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
}
