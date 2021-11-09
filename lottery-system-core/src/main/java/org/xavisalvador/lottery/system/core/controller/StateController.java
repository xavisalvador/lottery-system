package org.xavisalvador.lottery.system.core.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/state")
public class StateController {

	private static final Logger LOGGER = LoggerFactory.getLogger(StateController.class);

	/**
	 * Check that the service is working.
	 *
	 * @return {@link org.springframework.http.HttpStatus} 200 and {@link String} "UP" whether it is working.
	 */
	@GetMapping(produces = MediaType.TEXT_PLAIN_VALUE)
	public String getState() {
		LOGGER.info(">> getState()");
		LOGGER.info("<< getState()");
		return "UP";
	}

}
