package it.uniroma3.siw.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.model.Credentials;
import it.uniroma3.siw.spring.model.Maestro;
import it.uniroma3.siw.spring.service.MaestroService;

@Controller
public class MaestroController {
	
	@Autowired
	private MaestroService maestroService;
	
    @Autowired
    private MaestroValidator maestroValidator;
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    

    @RequestMapping(value="/addMaestro", method = RequestMethod.GET)
    public String addMaestro(Model model) {
    	logger.debug("addMaestro");
    	model.addAttribute("maestro", new Maestro());
    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Credentials credentials = maestroService.getCredentialsService().getCredentials(userDetails.getUsername());
    	model.addAttribute("credentials", credentials);
        return "maestroForm.html";
    }

    @RequestMapping(value = "/maestro/{id}", method = RequestMethod.GET)
    public String getMaestro(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("maestro", this.maestroService.maestroPerId(id));
    	return "maestro.html";
    }

    @RequestMapping(value = "/maestro", method = RequestMethod.GET)
    public String getMaestri(Model model) {
    		model.addAttribute("maestri", this.maestroService.tutti());
    		return "maestri.html";
    }
    
    @RequestMapping(value = "/maestro", method = RequestMethod.POST)
    public String newMaestro(@ModelAttribute("maestro") Maestro maestro, 
    									Model model, BindingResult bindingResult) {
    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Credentials credentials = maestroService.getCredentialsService().getCredentials(userDetails.getUsername());
    	model.addAttribute("credentials", credentials);
    	this.maestroValidator.validate(maestro, bindingResult);
        if (!bindingResult.hasErrors()) {
        	maestro.setCircolo(credentials.getUser().getCircolo());
        	this.maestroService.inserisci(maestro);
            model.addAttribute("maestri", this.maestroService.tutti());
            model.addAttribute("utente", credentials.getUser());
            return "home2.html";
        }
        return "maestroForm.html";
    }
}
