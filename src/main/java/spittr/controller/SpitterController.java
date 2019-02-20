package spittr.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.annotation.ErrorsMethodArgumentResolver;

import spittr.domain.Spitter;
import spittr.service.ISpitterService;

@Controller
@RequestMapping("spitter")
public class SpitterController {
	
	private ISpitterService spitterService;

	public SpitterController() {
	}

	@Autowired
	public SpitterController(ISpitterService spitterService) {
		this.spitterService = spitterService;
	}

	@RequestMapping("register")
	public String showRegisterForm() {
		return "registerForm";
	}
	
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String processRegistration(@Valid Spitter spitter, Errors errors, Model model) {
		if(errors.hasErrors())
			return "registerForm";
		spitterService.save(spitter);
		model.addAttribute("username",spitter.getUsername());
		model.addAttribute("spitterId",spitter.getId());
		return "redirect:/spitter/{username}";
	}
	
	@RequestMapping("{username}")
	public String showSpitterProfile(@PathVariable String username, Model model) {
		Spitter spitter = spitterService.findByUsername(username);
		model.addAttribute("spitter", spitter);
		return "profile";
	}
}
