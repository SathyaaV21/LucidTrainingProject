package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.RTM;
import com.example.demo.service.rtmService;

@RestController
@RequestMapping("/api/v1")
public class rtmController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(rtmController.class);
	
	@Autowired
	private rtmService rtmservice;
	
	@GetMapping("/getrtm")
	public List<RTM> getRTM() {
		LOGGER.info("IN GET ALL FILES");
		return rtmservice.getRTM();
	}
	
	@PostMapping("/addrequirement")
	public RTM addemployee(@RequestBody RTM rtm) {
		return rtmservice.addRequirement(rtm);
	}
}
