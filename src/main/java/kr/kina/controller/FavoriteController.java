package kr.kina.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.kina.domain.FavoriteSongVO;
import kr.kina.service.FavoriteService;

@Controller
@RequestMapping("/favorite")
public class FavoriteController {

	private static final Logger log = LoggerFactory.getLogger(FavoriteController.class);
	
	@Inject
	FavoriteService service;
	
	/*
	 * When clicked 'like it' button
	 * */
	@RequestMapping(value="/savedSong", method=RequestMethod.POST, consumes="application/json; charset=UTF-8")
	@ResponseBody
	public String savedSong(@RequestBody FavoriteSongVO vo, Model model) throws Exception {
		log.info("like it and save song in myMusic page");
		String resultMsg = "";
		Object duplicateCheck = service.searchDuplicate(vo.getUserid(), vo.getTitle());

		if(duplicateCheck.toString().equals(vo.getUserid())){
			resultMsg = "�̹� '���ƿ�'�� ���� ���Դϴ�.";
		}else{
			service.likeitSong(vo); //insert DB
			resultMsg = "���̹����� �ش� ���� ����Ǿ����ϴ�.";
		}
		ObjectMapper mapper = new ObjectMapper();
		resultMsg = mapper.writeValueAsString(resultMsg);
		return resultMsg;
	}	
}
