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
	
	@RequestMapping(value="/searchArtists", method=RequestMethod.GET)
	public void searchArtists(@RequestParam("query") String searchTxt, Model model) throws Exception {
		log.info("searchArtist Controller .... ");
		
		List<ArtistVO> artistVO = service.searchArtist(searchTxt);
		
		model.addAttribute("artistList", artistVO);
		model.addAttribute("artistNum", artistVO.size());
		model.addAttribute("searchTxt", searchTxt);
	}
	
	@RequestMapping(value="/searchSongs", method=RequestMethod.GET)
	public void searchSongs(@RequestParam("query") String searchTxt, Model model) throws Exception {
		log.info("searchSongs Controller .... ");
		
		List<SongVO> songVO = service.searchSong(searchTxt);
		
		model.addAttribute("songList", songVO);
		model.addAttribute("songNum", songVO.size());
		model.addAttribute("searchTxt", searchTxt);
	}
	
	@RequestMapping(value="/searchAlbums", method=RequestMethod.GET)
	public void searchAlbums(@RequestParam("query") String searchTxt, Model model) throws Exception {
		log.info("searchAlbums controller .... ");
		
		List<AlbumVO> albumVO = service.searchAlbum(searchTxt);
		
		model.addAttribute("albumList", albumVO);
		model.addAttribute("albumNum", albumVO.size());
		model.addAttribute("searchTxt", searchTxt);
	}
}
