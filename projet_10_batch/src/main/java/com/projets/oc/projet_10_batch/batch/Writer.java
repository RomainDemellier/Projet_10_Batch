package com.projets.oc.projet_10_batch.batch;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.projets.oc.projet_10_batch.model.Reservation;
import com.projets.oc.projet_10_batch.service.TokenService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Writer implements ItemWriter<Long> {

	private WebClient webClient;
	private TokenService tokenService;
	
	public Writer(TokenService tokenService) {
		// TODO Auto-generated constructor stub
		this.webClient = WebClient.create("http://localhost:8080");
		this.tokenService = tokenService;
	}
	
	@Override
	public void write(List<? extends Long> items) throws Exception {
		// TODO Auto-generated method stub
		String token = this.tokenService.getToken();
		for(Long id: items) {
			System.out.println("Dans Writer id : " + id);
			this.webClient.method(HttpMethod.DELETE).uri("/api/reservation/delete/" + id).headers(h -> h.setBearerAuth(token)).exchange().subscribe(res -> System.out.println("DELETE: " + res.statusCode()));
		}
	}

}
