package kr.kina.persistence;

import kr.kina.domain.MemberVO;

public interface MemberDAO {

	void signUp(MemberVO vo) throws Exception;//ȸ������ (insert)
	void login(String id, String password) throws Exception; //�α��� üũ 

}
