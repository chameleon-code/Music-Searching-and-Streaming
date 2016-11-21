package kr.kina.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.kina.domain.StreamingVO;
import kr.kina.service.CounterService;

@Controller
@RequestMapping("/player")
public class PlayerController {

	static final Logger log = LoggerFactory.getLogger(PlayerController.class);
	
	@Inject
	CounterService service;	
	
	
	/** ���⼭ RequestParam���� filepath�� �޾Ƽ�, 
	 *  ���� ���� ���, List<String>���� �޾Ƽ�,
	 *  ���⼭ inputstream���� D:���� ������ �о�ͼ� 
	 *  
	 * */
	@RequestMapping(value="/webplayer", method=RequestMethod.GET)
	public void player(@RequestParam("no") String no) throws Exception{
		log.info("webplayer popup window -- 2 ");
	}
	
	@RequestMapping(value="/webplayer", method=RequestMethod.POST, consumes="application/json; charset=UTF-8")
	@ResponseBody
	public String playerPOST(@RequestBody StreamingVO obj) throws Exception {
		log.info("webplayer data coming -- 1");
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(obj);
		
		return jsonData;	
	}
	
	
	
}
