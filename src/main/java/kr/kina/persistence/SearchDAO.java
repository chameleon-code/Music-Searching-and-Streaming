package kr.kina.persistence;

import java.util.List;

import kr.kina.domain.AlbumVO;
import kr.kina.domain.ArtistVO;
import kr.kina.domain.Criteria;
import kr.kina.domain.SongVO;

public interface SearchDAO {

	List<ArtistVO> searchArtist(String searchText) throws Exception; //��Ƽ��Ʈ
	List<AlbumVO> searchAlbum(String searchText) throws Exception;  //�ٹ�
	List<SongVO> searchSong(String searchText) throws Exception;	//��
	
	List<ArtistVO> listPageArtist(Criteria criteria) throws Exception;
	List<AlbumVO> listPageAlbum(Criteria criteria) throws Exception;
	List<SongVO> listPageSong(Criteria criteria) throws Exception;
	

}
