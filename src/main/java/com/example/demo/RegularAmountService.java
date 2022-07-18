package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class RegularAmountService {

	private RegularAmount regularAmount;

	public RegularAmountService(RegularAmount regularAmount) {
		super();
		this.regularAmount = regularAmount;
	}
	
	public String getResult(RegularAmount regularAmount) {
		Float result = Float.parseFloat(regularAmount.getAmount())/regularAmount.getFrequency().getValue();
		return result.toString();
	}

	public RegularAmount getRegularAmount() {
		return regularAmount;
	}

	public void setRegularAmount(RegularAmount regularAmount) {
		this.regularAmount = regularAmount;
	}
}
