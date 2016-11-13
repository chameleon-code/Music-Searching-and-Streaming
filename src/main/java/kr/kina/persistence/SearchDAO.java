package kr.kina.persistence;

import java.util.List;

import kr.kina.domain.ArtistVO;

public interface SearchDAO {

	void searchAll(String searchText) throws Exception;    //��ü�˻�
	List<ArtistVO> searchArtist(String searchText) throws Exception; //��Ƽ��Ʈ
	void searchAlbum(String searchText) throws Exception;  //�ٹ�
	void searchSong(String searchText) throws Exception;	//��
	
}
