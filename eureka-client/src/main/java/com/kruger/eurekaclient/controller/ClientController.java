package com.kruger.eurekaclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@RestController
@RequestMapping("/client")
public class ClientController {

	@Autowired
	private EurekaClient eurekaClient;
	
	@Autowired
	private RestTemplateBuilder restTemplateBuilder;
	
	@GetMapping("/user")
	public String invokeServiceUsuario(){
		RestTemplate restTemplate = restTemplateBuilder.build();
		InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("msvc-user", false);
		String serviceBaseUrl = instanceInfo.getHomePageUrl();
		return restTemplate.getForObject(serviceBaseUrl+"/api/user", String.class);
	}
	
	@GetMapping("/course")
	public String invokeServiceCurso(){
		RestTemplate restTemplate = restTemplateBuilder.build();
		InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("msvc-course", false);
		String serviceBaseUrl = instanceInfo.getHomePageUrl();
		return restTemplate.getForObject(serviceBaseUrl+"/api/course", String.class);
	}
	
}
