package kr.kina.controller;

import java.util.List;

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
	public @ResponseBody String savedSong(@RequestBody FavoriteSongVO vo, Model model) throws Exception {
		log.info("like it and save song in myMusic page");
		String resultMsg = "";

		//logout
		if(vo.getUserid().equals("")){
			resultMsg = "�α��� �� ������ּ���.";
		}else { //login
			List<String> result = service.searchDuplicate(vo.getUserid(), vo.getTitle());
			int resultSize = result.size();
			
			if(resultSize == 0 || result.get(0) == null){
				System.out.println(resultSize);
				service.likeitSong(vo); //insert DB
				resultMsg = "���̹����� �ش� ���� ����Ǿ����ϴ�.";
			}else if ( resultSize != 0 && result.get(0).equals(vo.getUserid())){
				resultMsg = "�̹� ����� ���Դϴ�.";		
			}
		}
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(resultMsg);
	}
	
	@RequestMapping(value="/savedSong", method=RequestMethod.GET)
	public void savedSongGET() throws Exception {
		
	}
}
