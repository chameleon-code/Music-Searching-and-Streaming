package kr.kina.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.kina.domain.AudioUrlVO;
import kr.kina.domain.StreamingVO;
import kr.kina.service.CounterService;

@Controller
@RequestMapping("/player")
public class PlayerController {

	static final Logger log = LoggerFactory.getLogger(PlayerController.class);
	
	@Inject
	CounterService service;	
	
	
	/** audioURL������ִ� ��Ʈ�ѷ�
	 * 	@return audioURL������ �ѷ��� ��ü������ 
	 * */
	@RequestMapping(value="/webplayer", method=RequestMethod.POST, consumes="application/json; charset=UTF-8")
	public @ResponseBody List<Map<String,String>> getSongDataPOST(@RequestBody List<Map<String,String>> songData) throws Exception {
		log.info("Return audioURL for play song ...");
		
		List<Map<String,String>> returnData = songData;
		
		if( returnData.size() == 1){  //�� �� ���x
			String oneSrc = returnData.get(0).get("filePath").substring(13).replace('\\', '/' );
			oneSrc = "/songs" + oneSrc;
			returnData.get(0).put("audioSrc", oneSrc);
			System.out.println(oneSrc);
			returnData.get(0).remove("filePath");
		}
		if( returnData.size() > 1 ){  //���� ���
			for(Map<String,String> song : returnData){
				String src = song.get("filePath").substring(13).replace('\\','/');
				src = "/songs" + src;
				song.put("audioSrc", src);
				song.remove("filePath");
			}
		}
		return returnData;
	}
	
	/**
	 * popup player
	 * */
	@RequestMapping(value="/webplayer", method=RequestMethod.GET)
	public void popupWinGET()throws Exception {
		log.debug("webplayer Popup ..");
	}	
		
	
	/** addSong : �� �� ���
	 *  @return audisrc
	 * */
	@RequestMapping(value="/add", method=RequestMethod.POST, consumes="application/json; charset=UTF-8")
	@ResponseBody
	public String addSongPOST(@RequestBody Map<String,String> path) throws Exception {
		log.info("Add song");
		
		String addsongURL = path.get("filePath");
		addsongURL = addsongURL.substring(13).replace('\\', '/');
		addsongURL = "/songs" + addsongURL;
	
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(addsongURL);
	}
	
}
