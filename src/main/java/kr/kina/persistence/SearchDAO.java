package kr.kina.persistence;

import java.util.List;

import kr.kina.domain.AlbumVO;
import kr.kina.domain.ArtistVO;
import kr.kina.domain.SongVO;

public interface SearchDAO {

	void searchAll(String searchText) throws Exception;    //��ü�˻�
	List<ArtistVO> searchArtist(String searchText) throws Exception; //��Ƽ��Ʈ
	List<AlbumVO> searchAlbum(String searchText) throws Exception;  //�ٹ�
	List<SongVO> searchSong(String searchText) throws Exception;	//��
	
}
