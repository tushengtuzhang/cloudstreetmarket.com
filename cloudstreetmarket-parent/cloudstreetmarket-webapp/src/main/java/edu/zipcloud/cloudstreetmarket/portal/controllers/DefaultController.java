package edu.zipcloud.cloudstreetmarket.portal.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DefaultController {
	// http://localhost:8080/portal/whatever or
	// http://localhost:8080/portal/index URL with your browser.
	// /* All resources and directories at the level
	// /** All resources and directories at the level and sublevels
	@RequestMapping(value = "/*", method = { RequestMethod.GET, RequestMethod.HEAD })
	public String fallback() {
		return "index";
	}
}
