package edu.zipcloud.cloudstreetmarket.portal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.zipcloud.cloudstreetmarket.core.services.ICommunityService;
import edu.zipcloud.cloudstreetmarket.core.services.IMarketService;

@Controller
public class DefaultController {

	@Autowired
	private IMarketService marketService;

	@Autowired
	private ICommunityService communityService;

	// http://localhost:8080/portal/whatever or
	// http://localhost:8080/portal/index URL with your browser.
	// /* All resources and directories at the level
	// /** All resources and directories at the level and sublevels
	
	@RequestMapping(value = "/*", method = { RequestMethod.GET, RequestMethod.HEAD })
	public String fallback(Model model) {
		
		model.addAttribute("dailyMarketActivity", marketService.getLastDayMarketActivity("GDAXI"));
		model.addAttribute("dailyMarketsActivity", marketService.getLastDayMarketsOverview());
		model.addAttribute("recentUserActivity", communityService.getLastUserPublicActivity(10));
		
		return "index";
	}
}
