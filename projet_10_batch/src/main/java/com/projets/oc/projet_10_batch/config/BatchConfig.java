package com.projets.oc.projet_10_batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.projets.oc.projet_10_batch.batch.Processor;
import com.projets.oc.projet_10_batch.batch.Reader;
import com.projets.oc.projet_10_batch.batch.Writer;
import com.projets.oc.projet_10_batch.model.Reservation;
import com.projets.oc.projet_10_batch.service.TokenService;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	public JobLauncher jobLauncher;
	
	@Autowired
	public TokenService tokenService;
	
	@Value("${batch.mail}")
	public String email;

	@Value("${batch.password}")
	public String password;
	
	@Bean
	public Job processJob() {
		return jobBuilderFactory.get("processJob")
				.incrementer(new RunIdIncrementer()).listener(listener())
				.flow(orderStep1()).end().build();
	}

	@Bean Step orderStep1() {
		return stepBuilderFactory.get("orderStep1").<Reservation, Long>chunk(8)
				.reader(new Reader(tokenService,email,password)).processor(new Processor())
				.writer(new Writer(tokenService)).build();
	}

	@Bean
	JobExecutionListener listener() {
		return new JobExecutionListener() {

			@Override
			public void beforeJob(JobExecution jobExecution) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterJob(JobExecution jobExecution) {
				// TODO Auto-generated method stub
				System.out.println("BATCH DONE.");
			}
		};
	}
}
