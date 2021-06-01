package com.projets.oc.projet_10_batch.batch;

import org.springframework.batch.item.ItemProcessor;

import com.projets.oc.projet_10_batch.model.Reservation;

public class Processor implements ItemProcessor<Reservation, Long> {

	@Override
	public Long process(Reservation item) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Dans Processor id : " + item.getId());
		return item.getId();
	}

}
