package it.uniroma3.siw.spring.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Maestro;
import it.uniroma3.siw.spring.service.MaestroService;


@Component
public class MaestroValidator implements Validator {
	@Autowired
	private MaestroService maestroService;
	
    private static final Logger logger = LoggerFactory.getLogger(MaestroValidator.class);

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cognome", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "recapito", "required");		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "prezzoOraLezione", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sportInsegnato", "required");
		
		if (!errors.hasErrors()) {
			logger.debug("confermato: valori non nulli");
			if (this.maestroService.alreadyExists((Maestro)o)) {
				logger.debug("e' un duplicato");
				errors.reject("duplicato");
			}
		}
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return Maestro.class.equals(aClass);
	}
}
