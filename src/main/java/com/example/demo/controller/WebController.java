package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.util.Constants;


@RestController
@RequestMapping(Constants.Root_API)
public class WebController {
	
	
	@GetMapping( "/all" )
	public List<String> getAllItems(){
		List<String> newList = new ArrayList<>();
		newList.add("test");
		newList.add("test1");
		newList.add("test2");
		newList.add("test3");
		return newList;
	}
	
}
