package com.example.demo;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/nhs")
public class RegularAmountController {
 	
 @PostMapping(value = "/getRegularAmount", consumes = { "application/json" })
 public ResponseEntity<String> sayHello(@Valid RegularAmount regularAmount, BindingResult result, Model model) {
	 if (result.hasErrors()) {
		 return new ResponseEntity<>(result.getAllErrors().toString(), HttpStatus.BAD_REQUEST);
     }

	 return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
 }
}
