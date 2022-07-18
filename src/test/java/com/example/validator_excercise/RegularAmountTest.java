package com.example.validator_excercise;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.validator_excercise.RegularAmount.Frequency;

public class RegularAmountTest {
    private MockMvc mockMvc;    

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new RegularAmountController())
            .build();
    }


    @Test
    public void givenAnyFrequency_WhenNonNumericORBlankAmountProvided_ExpectValidationSuccess() throws Exception {
     mockMvc.perform(MockMvcRequestBuilders.post("/nhs/getRegularAmount")
    	           .contentType(MediaType.APPLICATION_JSON)
    	           .param("frequency", "WEEK")
    	            .param("amount", "")
    	           .accept(MediaType.APPLICATION_JSON))
    	 .andExpect(status().isOk());
    }
  
    @Test
    public void givenAnyFrequencyNotInEnum_WhenNonNumericORBlankAmountProvided_ExpectValidationSuccess() throws Exception {
     mockMvc.perform(MockMvcRequestBuilders.post("/nhs/getRegularAmount")
    	           .contentType(MediaType.APPLICATION_JSON)
    	           .param("frequency", "TEST")
    	            .param("amount", "")
    	           .accept(MediaType.APPLICATION_JSON))
    	 .andExpect(status().isBadRequest())
    	 .andExpect(content().string("[Field error in object 'regularAmount' on field 'frequency': rejected value [TEST]; codes [typeMismatch.regularAmount.frequency,typeMismatch.frequency,typeMismatch.com.example.validator_excercise.RegularAmount$Frequency,typeMismatch]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [regularAmount.frequency,frequency]; arguments []; default message [frequency]]; default message [Failed to convert property value of type 'java.lang.String' to required type 'com.example.validator_excercise.RegularAmount$Frequency' for property 'frequency'; nested exception is org.springframework.core.convert.ConversionFailedException: Failed to convert from type [java.lang.String] to type [@com.fasterxml.jackson.annotation.JsonProperty com.example.validator_excercise.RegularAmount$Frequency] for value 'TEST'; nested exception is java.lang.IllegalArgumentException: No enum constant com.example.validator_excercise.RegularAmount.Frequency.TEST]]"));
    }
   
    @Test
    public void givenAnyAmount_NullFrequencyProvided_ExpectNoValidationError() throws Exception {
     mockMvc.perform(MockMvcRequestBuilders.post("/nhs/getRegularAmount")
    	           .contentType(MediaType.APPLICATION_JSON)
    	           .param("frequency", "")
    	            .param("amount", "1000000000")
    	           .accept(MediaType.APPLICATION_JSON))
    	 .andExpect(status().isOk());
    }
    
    @Test
    public void givenWEEKFrequencey_AnyAmountProvided_ExpectNoValidationError() throws Exception {
     mockMvc.perform(MockMvcRequestBuilders.post("/nhs/getRegularAmount")
    	           .contentType(MediaType.APPLICATION_JSON)
    	           .param("frequency", "WEEK")
    	            .param("amount", "1343")
    	           .accept(MediaType.APPLICATION_JSON))
    	 .andExpect(status().isOk());
    }
    
    @Test
    public void givenMonthlyFrequencey_AnyAmountProvided_ExpectNoValidationError() throws Exception {
     mockMvc.perform(MockMvcRequestBuilders.post("/nhs/getRegularAmount")
    	           .contentType(MediaType.APPLICATION_JSON)
    	           .param("frequency", "MONTH")
    	            .param("amount", "1343")
    	           .accept(MediaType.APPLICATION_JSON))
    	 .andExpect(status().isOk());
    }
    
    @Test
    public void givenSetOfWeekFrequencey_AnyAmountProvided_ExpectNoValidationError() throws Exception {
    	Set<Frequency> weeks = new HashSet<>();
    	weeks.add(Frequency.TWO_WEEK);
    	weeks.add(Frequency.FOUR_WEEK);
    	weeks.add(Frequency.QUARTER);
    	weeks.add(Frequency.YEAR);
    	
    	for (Frequency frequency : weeks) {
    		 mockMvc.perform(MockMvcRequestBuilders.post("/nhs/getRegularAmount")
      	           .contentType(MediaType.APPLICATION_JSON)
      	           .param("frequency", frequency.name())
      	            .param("amount", "100")
      	           .accept(MediaType.APPLICATION_JSON))
      	 .andExpect(status().isOk());
		}
    
    }
    @Test
    public void givenSetOfWeekFrequencey_AnyAmountProvided_ExpectValidationError() throws Exception {
   
    		 mockMvc.perform(MockMvcRequestBuilders.post("/nhs/getRegularAmount")
      	           .contentType(MediaType.APPLICATION_JSON)
      	           .param("frequency", Frequency.QUARTER.name())
      	            .param("amount", "5000")
      	           .accept(MediaType.APPLICATION_JSON))
      	 .andExpect(status().isBadRequest())
      	 .andExpect(content().string("[Error in object 'regularAmount': codes [RegularAmountValueMatch.regularAmount,RegularAmountValueMatch]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [regularAmount.,]; arguments []; default message [],frequency,amount]; default message "
      	 		+ "[Amount can not be divided by the Number of Weeks to a whole number of pence provideds!]]"));
     
    
    }
  
}