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
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.spring.model.Circolo;
import it.uniroma3.siw.spring.model.Credentials;
import it.uniroma3.siw.spring.model.User;
import it.uniroma3.siw.spring.service.CircoloService;

@Controller
public class CircoloController {
	
	@Autowired
	private CircoloService circoloService;
	
    @Autowired
    private CircoloValidator circoloValidator;
        

    @RequestMapping(value="/addCircolo", method = RequestMethod.GET)
    public String addCircolo(Model model) {
    	model.addAttribute("circolo", new Circolo());
    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Credentials credentials = circoloService.getCredentialsService().getCredentials(userDetails.getUsername());
    	model.addAttribute("credentials", credentials);
        return "circoloForm.html";
    }

    @RequestMapping(value = "/circolo/{id}", method = RequestMethod.GET)
    public String getCircolo(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("circolo", this.circoloService.circoloPerId(id));
    	return "circolo.html";
    }

    @RequestMapping(value = "/circolo", method = RequestMethod.GET)
    public String getCircoli(Model model) {
    	
    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Credentials credentials = circoloService.getCredentialsService().getCredentials(userDetails.getUsername());
    	model.addAttribute("credentials", credentials);
    	model.addAttribute("circoli", this.circoloService.tutti());
    	return "circoli.html";
    }
    
    @RequestMapping(value = "/circolo", method = RequestMethod.POST)
    public String newCircolo(@ModelAttribute("circolo") Circolo circolo, 
    									Model model, BindingResult bindingResult) {
    	
    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Credentials credentials = circoloService.getCredentialsService().getCredentials(userDetails.getUsername());
    	model.addAttribute("credentials", credentials);
    	
    	this.circoloValidator.validate(circolo, bindingResult);
        if (!bindingResult.hasErrors()) {
        	this.circoloService.inserisci(circolo);
            model.addAttribute("circoli", this.circoloService.tutti());
            return "circoli.html";
        }
        return "circoloForm.html";
    }
    
    @RequestMapping(value = "/iscrizioneCircolo", method = RequestMethod.GET)
    public String iscrizioneCircolo(@ModelAttribute("circolo") Circolo circolo, 
    		 							BindingResult bindingResult, Model model) {
    	
    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Credentials credentials = circoloService.getCredentialsService().getCredentials(userDetails.getUsername());
    	User o=credentials.getUser();
    	model.addAttribute("credentials", credentials);
    	model.addAttribute("utente", o);
    	
    	model.addAttribute("circoli", this.circoloService.tutti());
    	return "iscrizioneCircolo.html";
    }
    
    @RequestMapping(value = "/selezionaCircolo", method = RequestMethod.POST)
    public String selezionaCircolo(@RequestParam("circolo") Long idCircolo, Model model) {
    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Credentials credentials = circoloService.getCredentialsService().getCredentials(userDetails.getUsername());
    	User o = credentials.getUser();
    	Circolo c = circoloService.circoloPerId(idCircolo);
    	o.setCircolo(c);
    	circoloService.getUserService().saveUser(o);
    	model.addAttribute("credentials", credentials);
    	model.addAttribute("utente", o);
    	return "home2.html";
    }
    
    @RequestMapping(value = "/disiscrizioneCircolo", method = RequestMethod.POST)
    public String disiscrivitiDaCircolo(Model model) {
    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Credentials credentials = circoloService.getCredentialsService().getCredentials(userDetails.getUsername());
    	User o = credentials.getUser();
    	o.setCircolo(null);
    	circoloService.getUserService().saveUser(o);
    	model.addAttribute("credentials", credentials);
    	model.addAttribute("utente", o);
    	return "home1.html";
    }
    
    
}