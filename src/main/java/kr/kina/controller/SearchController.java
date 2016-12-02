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
import kr.kina.domain.Criteria;
import kr.kina.domain.PaginationVO;
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
	public void searchMain(@RequestParam("keyword") String queryText, Model model) throws Exception { 
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
	public void searchArtists(Criteria criteria, Model model) throws Exception {
		log.debug("searchArtists controller");
		
		List<ArtistVO> artistVO = service.listPageArtist(criteria);
		
		model.addAttribute("artistList", artistVO);
		model.addAttribute("searchTxt", criteria.getKeyword());
		
		PaginationVO maker = new PaginationVO();
		maker.setCri(criteria);
		maker.setCount(service.searchArtist(criteria.getKeyword()).size());
		
		model.addAttribute("pagination", maker);
		
	}
	
	@RequestMapping(value="/searchSongs", method=RequestMethod.GET)
	public void searchSongs(Criteria criteria, Model model) throws Exception {
		log.debug("searchSongs Controller");
		
		List<SongVO> songVO = service.listPageSong(criteria);
		int totalCount = service.searchSong(criteria.getKeyword()).size();
				
		model.addAttribute("songList", songVO);
		model.addAttribute("searchTxt", criteria.getKeyword());
		
		PaginationVO maker = new PaginationVO();
		maker.setCri(criteria);
		maker.setCount(totalCount);

		model.addAttribute("pagination", maker);

		//make list numbering
		int curPage = criteria.getPage(); //���� ������
		int startNum; 
		
		if(curPage > 1){
			startNum = curPage + (curPage-1) * 15;
			model.addAttribute("startNum", startNum);
		}else if(curPage == 1){
			model.addAttribute("startNum", Integer.parseInt("1"));			
		}
	}
	
	@RequestMapping(value="/searchAlbums", method=RequestMethod.GET)
	public void searchAlbums(Criteria criteria, Model model) throws Exception {
		log.info("searchAlbums controller .... ");
		
		List<AlbumVO> albumVO = service.listPageAlbum(criteria);
		
		model.addAttribute("albumList", albumVO);
		model.addAttribute("searchTxt", criteria.getKeyword());
		
		PaginationVO maker = new PaginationVO();
		maker.setCri(criteria);		
		maker.setCount(service.searchAlbum(criteria.getKeyword()).size());
		
		model.addAttribute("pagination", maker);
	}
	
	
}
