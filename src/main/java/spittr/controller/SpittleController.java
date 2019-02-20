package spittr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import spittr.domain.Spittle;
import spittr.service.ISpittleService;

@Controller
@RequestMapping("spittles")
public class SpittleController {
	
	private ISpittleService spittleService;
	
	@Autowired
	public SpittleController(ISpittleService spittleService) {
		this.spittleService = spittleService;
	}

	@RequestMapping
	public String spittles(Model model) {
		model.addAttribute("spittles",spittleService.getSpittles(Long.MAX_VALUE, 20));
		return "spittles";
	}
	
	@RequestMapping("page")
	public String pagedSpittles(
			@RequestParam(value = "max", defaultValue = "111111111111111111111") long max, 
			@RequestParam(value = "count", defaultValue = "20") int count, 
			Model model) {
		model.addAttribute("spittles", spittleService.getSpittles(max, count));
		return "spittles";
	}
	
	@RequestMapping("{spittleId}")
	public String showPathvariableSpittles(@PathVariable long spittleId, Model model) {
		Spittle spittle = spittleService.getSpittle(spittleId);
		if(spittle == null)  ;
		model.addAttribute("spittle", spittle);
		return "spittle";
	}
}
