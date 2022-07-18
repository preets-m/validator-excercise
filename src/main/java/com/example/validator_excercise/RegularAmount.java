package com.example.validator_excercise;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@RegularAmountValueMatch.List({ 
    @RegularAmountValueMatch(
      field = "frequency", 
      fieldMatch = "amount", 
      message = "Amount can not be divided by the Number of Weeks to a whole number of pence provideds!"
    )
})
@Component
public class RegularAmount {
	@JsonProperty("frequency")
	 private Frequency frequency;
	
	@JsonProperty("amount")
	 private String amount;
	 
	
	 public RegularAmount() {
	
	}

	public RegularAmount(Frequency frequency, String amount) {
		super();
		this.frequency = frequency;
		this.amount = amount;
	}
	 
	public Frequency getFrequency() {
	 return frequency;
	 }
	 public void setFrequency(Frequency frequency) {
	 this.frequency = frequency;
	 }
	 
	 public String getAmount() {
		
	 return amount;
	 }
	 
	 
	 public void setAmount(String amount) {
	 this.amount = amount;
	 }
	 
	 public enum Frequency {
		 WEEK(1), TWO_WEEK(2), FOUR_WEEK(4), MONTH(4), QUARTER(13), YEAR(52);

		 private final int numberOfWeeks;

		 Frequency(final int weeks) {
			 numberOfWeeks = weeks;
	        }

	        public int getValue() { return numberOfWeeks; }
		}

	 @Override
	  public String toString() {
	    return "Regular Amount [frequency=" + frequency + ", amount=" + amount+"]";
	  }
}

