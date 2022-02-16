package it.uniroma3.siw.spring.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Campo;
import it.uniroma3.siw.spring.service.CampoService;


@Component
public class CampoValidator implements Validator {
	@Autowired
	private CampoService campoService;


    private static final Logger logger = LoggerFactory.getLogger(CampoValidator.class);
	
	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numeroCampo", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sport", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "prezzoOrario", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "terreno", "required");

		if (!errors.hasErrors()) {
			logger.debug("confermato: valori non nulli");
			if (this.campoService.alreadyExists((Campo)o)) {
				logger.debug("e' un duplicato");
				errors.reject("duplicato");
			}
		}
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return Campo.class.equals(aClass);
	}
}
