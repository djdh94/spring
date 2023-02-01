package com.ict.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ict.domain.ReplyVO;
import com.ict.service.ReplyService;

@RestController
@RequestMapping("/replies")
public class ReplyController {

	@Autowired
	private ReplyService service;
	
	
	// consumes는 이 메서드의 파라미터를 넘겨줄때 어떤형식으로 넘겨줄지를 설정하는데
	// 기본적으로 rest방식에는 json을 사용
	@PostMapping(value = "",consumes="application/json",
							produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> register(@RequestBody ReplyVO vo){
		ResponseEntity<String> entity=null;
		try {
			service.addRepaly(vo);
			entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
		}catch(Exception e) {
			entity = new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@GetMapping(value = "/all/{bno}",
						produces= {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<ReplyVO>> list(@PathVariable("bno") Long bno){
		ResponseEntity<List<ReplyVO>> entity=null;
		try {
			entity=new ResponseEntity<>(
					service.listReply(bno),HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			entity=new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	
}
