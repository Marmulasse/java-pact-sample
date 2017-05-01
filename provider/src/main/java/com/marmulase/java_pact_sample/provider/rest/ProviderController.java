package com.marmulase.java_pact_sample.provider.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {

	@RequestMapping(path = "/api/provider/sample", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity get() {
		return ResponseEntity.ok(new SampleObject("coucou"));
	}
	
	public class SampleObject {
		private String label;

		public SampleObject(String label) {
			this.label = label;
		}

		public String getLabel() {
			return label;
		}
	}
}
