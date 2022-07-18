package com.example.validator_excercise;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;

import com.example.validator_excercise.RegularAmount.Frequency;

public class RegularAmountValueMatchValidator implements ConstraintValidator<RegularAmountValueMatch, Object> {

	private String field;
	private String fieldMatch;

	public void initialize(RegularAmountValueMatch constraintAnnotation) {
		this.field = constraintAnnotation.field();
		this.fieldMatch = constraintAnnotation.fieldMatch();
	}

	public boolean isValid(Object value, ConstraintValidatorContext context) {

		Frequency fieldValue = (Frequency) new BeanWrapperImpl(value).getPropertyValue(field);
		String fieldMatchValue = (String) new BeanWrapperImpl(value).getPropertyValue(fieldMatch);

		if (Objects.nonNull(fieldValue) && Objects.nonNull(fieldMatchValue) && !fieldMatchValue.equals("")) {
			Float result = Float.parseFloat(fieldMatchValue) / fieldValue.getValue();

			String stringVal = String.valueOf(result.doubleValue());
			if (stringVal.contains("."))
				try {
					String afterDecimalPoint = stringVal.substring(stringVal.indexOf(".") + 1, stringVal.length() - 1);
					if (afterDecimalPoint.length() > 2) {
						return false;
					}
				} catch (Exception e) {
					return true;
				}

		}
		return true;
	}

}
