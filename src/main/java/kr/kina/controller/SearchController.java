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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.kina.domain.AlbumVO;
import kr.kina.domain.ArtistVO;
import kr.kina.domain.SongVO;
import kr.kina.service.SearchService;

@Controller
@RequestMapping("/search")
public class SearchController {

	static final Logger log = LoggerFactory.getLogger(SearchController.class);
	
	@Inject
	SearchService service;
	
	/** searchMain(���հ˻�)
	 *  @param query
	 * */
	@RequestMapping(value="/searchMain", method=RequestMethod.GET)
	public void searchMain(@RequestParam("query") String queryText, Model model) throws Exception { 
		log.info("searchMain Controller .. ");
		
		List<SongVO> songVO = service.searchSong(queryText);
		List<ArtistVO> artistVO = service.searchArtist(queryText);
		List<AlbumVO> albumVO = service.searchAlbum(queryText);
				
		//�˻���: null
		if(queryText.equals(null)){
			log.info("-----------------------");
			model.addAttribute("searchText", queryText);
		
		}else {
			model.addAttribute("searchText", queryText);
			
			//�� �˻� - 10��
			model.addAttribute("songList", songVO);
			model.addAttribute("songNum", songVO.size());
			
			//��Ƽ��Ʈ �˻� - 6 (3x3)
			model.addAttribute("artistList", artistVO);
			model.addAttribute("artistNum", artistVO.size());
			
			//�ٹ� �˻� - 6 (3x3)
			model.addAttribute("albumList", albumVO);
			model.addAttribute("albumNum", albumVO.size());
		}	
	}
	
	@RequestMapping(value="/searchMain", method=RequestMethod.POST)
	public void searchMainPost(@RequestParam("title") String title,
			@RequestParam("artist") String artist,
			@RequestParam("album") String album,
			@RequestParam("duration") String duration,
			@RequestParam("filePath") String filepath
			) throws Exception {
		System.out.println("�˾��� ����ָ� �ȴ�.");
		//���⼭ �˾��� ����ִ°���..web
	}
	
	@RequestMapping(value="/searchArtist", method=RequestMethod.GET)
	public void searchArtist(@RequestParam("query") String txt, Model model) throws Exception {
		log.info("searchArtist Controller .... ");
	}
}
