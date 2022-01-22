package com.myspring.afterdish.member.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.dao.DataAccessException;
import com.myspring.afterdish.member.dao.MemberDAO;
import com.myspring.afterdish.member.vo.MemberVO;

@Service("memberService")
@Transactional(propagation = Propagation.REQUIRED)
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDAO memberDAO;
	
	@Override
	public List listMember() throws DataAccessException{
		List memberList = null;
		memberList = memberDAO.selectAllMemberList();
		return memberList;
	}
	
	@Override
	public int addMember(MemberVO member) throws DataAccessException{
		return memberDAO.insertMember(member);
	}
	
	@Override
	public int removeMember(String id) throws DataAccessException{
		return memberDAO.deleteMember(id);
	}
	
	@Override
	public MemberVO login(MemberVO memberVO) throws Exception{
		return memberDAO.loginById(memberVO);
	}
	
	@Override
	public int checkDuplicationById(String id)throws DataAccessException{
		return memberDAO.checkDuplicationById(id);
	}
}
