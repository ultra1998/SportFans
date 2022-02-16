package it.uniroma3.siw.spring.controller;

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

import it.uniroma3.siw.spring.model.Campo;
import it.uniroma3.siw.spring.model.Circolo;
import it.uniroma3.siw.spring.model.Credentials;
import it.uniroma3.siw.spring.service.CampoService;

@Controller
public class CampoController {
	
	@Autowired
	private CampoService campoService;
	
    @Autowired
    private CampoValidator campoValidator;
    

    @RequestMapping(value="/addCampo", method = RequestMethod.GET)
    public String addCampo(Model model) {
    	model.addAttribute("campo", new Campo());
    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Credentials credentials = campoService.getCredentialsService().getCredentials(userDetails.getUsername());
    	model.addAttribute("credentials", credentials);
        return "campoForm.html";
    }

    @RequestMapping(value = "/campo/{id}", method = RequestMethod.GET)
    public String getCampo(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("campo", this.campoService.campoPerId(id));
    	return "campo.html";
    }

    @RequestMapping(value = "/getCampo", method = RequestMethod.GET)
    public String getCampi(Model model) {
    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Credentials credentials = campoService.getCredentialsService().getCredentials(userDetails.getUsername());
    	Circolo c=credentials.getUser().getCircolo();
    	model.addAttribute("utente", credentials.getUser());
    	model.addAttribute("credentials", credentials);
    	model.addAttribute("campi", this.campoService.filtraPerCircolo(c));
    	return "campi.html";
    }
    
    @RequestMapping(value = "/campo", method = RequestMethod.POST)
    public String newCampo(@ModelAttribute("campo") Campo campo, 
    									Model model, BindingResult bindingResult) {
    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Credentials credentials = campoService.getCredentialsService().getCredentials(userDetails.getUsername());
    	Circolo c=credentials.getUser().getCircolo();
    	campo.setCircolo(c);
    	model.addAttribute("utente", credentials.getUser());
    	model.addAttribute("credentials", credentials);
    	this.campoValidator.validate(campo, bindingResult);
        if (!bindingResult.hasErrors()) {
        	this.campoService.inserisci(campo);
            model.addAttribute("campi", this.campoService.filtraPerCircolo(c));
            return "campi.html";
        }
        return "campoForm.html";
    }
}
