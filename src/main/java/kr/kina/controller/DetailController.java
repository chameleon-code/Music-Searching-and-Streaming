package kr.kina.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.kina.domain.SongVO;
import kr.kina.service.DetailService;

@Controller
@RequestMapping("/detail")
public class DetailController {

	private static final Logger log = LoggerFactory.getLogger(DetailController.class);
	private String namespace="kr.kina.mappers.DetailMapper";
	
	@Inject
	DetailService service;
	
	
	@RequestMapping(value="/artist", method=RequestMethod.GET)
	public void resultArtist(@RequestParam("artist") String artist, Model model) throws Exception {
		log.debug("Artist Detail Controller .... ");
		
		List<SongVO> songVO = service.artistPage(artist);  //title, duration, album, tracknum, artist, filepath--> url
		int size = songVO.size();	
		if(size >= 1){
			for(SongVO vo : songVO){
				String url = vo.getFilePath().substring(13).replace('\\', '/');
				url = "/songs" + url;
				vo.setFilePath(url);
				model.addAttribute("resultArtistSize", size);	
			}		
		}else{
			model.addAttribute("resultArtistSize", "�� �����ϴ�.");
		}
		
		model.addAttribute("SongByArtist", songVO);
		model.addAttribute("artistTxt", artist);
	}
	
	
	@RequestMapping(value="/album", method=RequestMethod.GET)
	public void resultAlbum(@RequestParam("id") String id ) throws Exception {
		log.debug("Album Detail Controller .... ");

		
	}
		

}
