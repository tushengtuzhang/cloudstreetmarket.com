package edu.zipcloud.cloudstreetmarket.portal.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.StringJoiner;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/info")
public class InfoTagController {

	// 关联到了index.jsp页面了 根据dispatcher-context的prefix和suffix
	@RequestMapping(value = "/index")
	public ModelAndView getRequestExample(ServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		mav.addObject("variable1", new ArrayList<String>());
		return mav;
	}

	// http://localhost:8080/portal/info/index2?exP1=abc
	@RequestMapping(value = "/index2")
	public ModelAndView getRequestExample(@RequestParam("exP1") String exP1) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index2");
		mav.addObject("exP1", exP1);
		return mav;
	}

	@RequestMapping("/helloHandler")
	@ResponseBody
	public String helloController() {
		return "hello";
	}

	@Autowired
	private WebApplicationContext webAppContext;
	private final static LocalDateTime startDateTime = LocalDateTime.now();
	private final static DateTimeFormatter DT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	@RequestMapping("/server")
	@ResponseBody
	public String infoTagServer() {
		return new StringJoiner("<br>").add("-------------------------------------")
				.add(" Server: " + webAppContext.getServletContext().getServerInfo())
				.add(" Start date: " + startDateTime.format(DT_FORMATTER))
				.add(" Version: " + webAppContext.getBean("webAppVersion"))
				.add("--------------------------------------").toString();
	}

	// http://localhost:8080/portal/info/example/123
	@RequestMapping(value = "/example/{param}")
	public HttpEntity<String> example(@PathVariable("param") String parameter) {
		return new HttpEntity<>(parameter);
	}
	// http://localhost:8080/portal/info/example2/123
	@RequestMapping(value = "/example2/{param}")
	public HttpEntity<String> example2(@PathVariable String param) {
		return new HttpEntity<>(param);
	}
}
