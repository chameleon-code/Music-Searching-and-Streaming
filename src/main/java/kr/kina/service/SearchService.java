package kr.kina.service;

import java.util.List;

import kr.kina.domain.ArtistVO;

public interface SearchService {

	List<ArtistVO> searchArtist(String searchText) throws Exception;  //��Ƽ��Ʈ �˻�
}
