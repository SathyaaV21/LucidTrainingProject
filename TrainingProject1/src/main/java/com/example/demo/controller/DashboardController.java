package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ProjectNotFoundException;
import com.example.demo.model.RTM;
import com.example.demo.service.DashboardService;

@RequestMapping("/api/v1/dashboard")
@RestController
public class DashboardController {
	
	private DashboardService dashboardService;
	
	@GetMapping("/rtm")
	public List<RTM> rtm() throws ProjectNotFoundException
	{
		return dashboardService.getRTM();
	}

}
