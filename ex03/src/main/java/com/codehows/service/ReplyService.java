package com.codehows.service;

import java.util.List;

import com.codehows.domain.Criteria;
import com.codehows.domain.ReplyVO;


//mapper 안에 insert
public interface ReplyService {
	
	public int register(ReplyVO vo);
	
	public ReplyVO get(Long rno);
	
	public int modify(ReplyVO vo);
	
	public int remove(Long rno);
	
	public List<ReplyVO> getList(Criteria cri, Long bno);
}
