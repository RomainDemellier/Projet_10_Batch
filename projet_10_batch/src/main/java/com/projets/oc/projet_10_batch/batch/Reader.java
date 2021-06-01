package com.projets.oc.projet_10_batch.batch;

import java.awt.image.DataBuffer;
import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.BodyExtractor;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projets.oc.projet_10_batch.model.Credentials;
import com.projets.oc.projet_10_batch.model.Reservation;
import com.projets.oc.projet_10_batch.service.TokenService;

import io.netty.handler.codec.http.HttpResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Reader implements ItemReader<Reservation> {

	private List<Reservation> reservations = new ArrayList<Reservation>();
	private WebClient webClient;
	private int count = 0;
	private JsonNode jsonNode;
	private ObjectMapper objectMapper;
	private TokenService tokenService;
	private String email;
	private String password;
	
	public Reader(TokenService tokenService, String email, String password)  {
		// TODO Auto-generated constructor stub
		this.webClient = WebClient.create();
		this.objectMapper = new ObjectMapper();
		this.tokenService = tokenService;
		this.email = email;
		this.password = password;
		System.out.println(this.email + " , " + this.password);
	}
	
	@Override
	public Reservation read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		// TODO Auto-generated method stub
		String json = "{ \"username\": \"" + this.email + "\", \"password\":\"" + this.password +"\" }";
		System.out.println(json);
		//ObjectMapper objectMapper = new ObjectMapper();
		this.jsonNode = this.objectMapper.readTree(json);
		
//		System.out.println("post " + this.webClient.post()
//			    .uri("http://localhost:8080/authenticate")
//			    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//			    .bodyValue(this.jsonNode)
//			    .exchange()
//			    .block().bodyToMono(String.class));
		String result;
		
		result =  this.webClient.post()
	    .uri("http://localhost:8080/authenticate")
	    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
	    .bodyValue(this.jsonNode)
	    .exchange()
	    .block()
	    .bodyToMono(String.class)
	    .block();
		
		JsonNode jsonNodeTokeNode = this.objectMapper.readTree(result);
		System.out.println("token : " + jsonNodeTokeNode.get("token"));
		tokenService.setToken(jsonNodeTokeNode.get("token").asText());
		String token = tokenService.getToken();
	    //.subscribe(res -> System.out.println(res.body(String)));
		
		//En local
		Flux<Reservation> flux = this.webClient.get().uri("http://localhost:8080/api/reservation/dateLimitDepasse").headers(headers -> headers.setBearerAuth(token))
				.retrieve().bodyToFlux(Reservation.class);
		
		this.reservations = flux.collectList().block();
		
		if(count < this.reservations.size()) {
			
			System.out.println("Dans le Batch");
			System.out.println("Réservation : " + this.reservations.get(count).getId() + " Usager : " + this.reservations.get(count).getUsager().getId());
			return this.reservations.get(count++);
		} else {
			count = 0;
		}
		
		return null;
	}

}
