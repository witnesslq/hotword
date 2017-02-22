package com.sohu.mrd.hotword.controller;

import com.sohu.mrd.hotword.service.GetHotWordRedisDate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;

import java.util.Map;


/**
 * @author ideawu
 * 
 */
@Controller
public class HotWordController {
	/*
	 * private HelloManager helloManager;
	 * 
	 * public void setHelloManager(HelloManager helloManager) {
	 * this.helloManager = helloManager; }
	 */
	@RequestMapping(value = "/hotword.do")
		 public ModelAndView handleRequest(String dataHour,int topN,Model model) throws Exception {

		Map<String,Integer> h= GetHotWordRedisDate.getGetRedisDate(dataHour, topN);
		model.addAttribute("h",h);
		return new ModelAndView("hotword");
	}

	@RequestMapping(value = "/index.do")
	public ModelAndView handleRequest() throws Exception {

		return new ModelAndView("index");
	}

}
