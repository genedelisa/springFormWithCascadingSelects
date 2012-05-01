package com.rockhoppertech.spring.web;

import java.util.Set;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rockhoppertech.spring.domain.City;
import com.rockhoppertech.spring.domain.State;
import com.rockhoppertech.spring.service.GeoService;

/**
 * @author gene
 * 
 */
@Controller
public class RegistrationController {

	private static final Logger logger = LoggerFactory
			.getLogger(RegistrationController.class);

	@Inject
	private GeoService geoService;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String get(Model model) {
		SignupForm form = new SignupForm();
		model.addAttribute("signupForm", form);
		logger.debug("registering. added form to model and returning register page");
		return "register";
	}

	@RequestMapping(value = "/cities", method = RequestMethod.GET)
	public @ResponseBody
	Set<City> citiesForState(
			@RequestParam(value = "stateName", required = true) String state) {
		logger.debug("finding cities for state " + state);
		return this.geoService.findCitiesForState(state);
	}

	@RequestMapping(value = "/states", method = RequestMethod.GET)
	public @ResponseBody
	Set<State> findAllStates() {
		logger.debug("finding all states");
		return this.geoService.findAllStates();
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@ModelAttribute("signupForm") SignupForm form,
			BindingResult result) {
		logger.debug("siging up " + form);
		return "account";
	}

}
