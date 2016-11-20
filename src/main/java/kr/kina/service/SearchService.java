package kr.kina.service;

import java.util.List;

import kr.kina.domain.AlbumVO;
import kr.kina.domain.ArtistVO;
import kr.kina.domain.SongVO;

public interface SearchService {

	List<ArtistVO> searchArtist(String searchText) throws Exception;  //��Ƽ��Ʈ �˻�
	List<SongVO> searchSong(String searchText) throws Exception;  //�� �˻�
	List<AlbumVO> searchAlbum(String searchText) throws Exception; //�ٹ� �˻�
}
