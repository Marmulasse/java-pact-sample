package com.marmulase.java_pact_sample.provider.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {

	@RequestMapping(path = "/api/provider/sample", method = RequestMethod.GET)
	public String get() {
		return "coucou";
	}
}
