package spittr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spittr.domain.Spitter;
import spittr.service.ISpitterService;

import javax.validation.Valid;

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
	public String processRegistration(@Valid Spitter spitter, Errors errors, RedirectAttributes model) {
		if(errors.hasErrors())
			return "registerForm";
		spitterService.save(spitter);
		model.addAttribute("username",spitter.getUsername());
//		model.addAttribute("spitterId",spitter.getId());
		model.addFlashAttribute(spitter);

		return "redirect:/spitter/{username}";
	}
	
	@RequestMapping("{username}")
	public String showSpitterProfile(@PathVariable String username, Model model) {
		if (!model.containsAttribute("spitter")){
			Spitter spitter = spitterService.findByUsername(username);
			model.addAttribute("spitter", spitter);
		}
		return "profile";
	}
}
