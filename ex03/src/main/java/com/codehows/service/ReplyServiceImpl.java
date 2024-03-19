package com.codehows.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codehows.domain.Criteria;
import com.codehows.domain.ReplyVO;
import com.codehows.mapper.ReplyMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
@Service
@Log4j
public class ReplyServiceImpl implements ReplyService{
	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
	
	@Override
	public int register(ReplyVO vo) {
		log.info("앙 기모띠....."+vo);
		return mapper.insert(vo);
	}
	@Override
	public ReplyVO get(Long rno) {
		log.info("get......."+rno);
		return mapper.read(rno);
	}
	
	@Override
	public int modify(ReplyVO vo) {
		log.info("modify......."+vo);
		return mapper.update(vo);
	}
	
	@Override
	public int remove(Long rno) {
		log.info("delete......."+rno);
		return mapper.delete(rno);
	}
	
	@Override
	public List<ReplyVO> getList(Criteria cri, Long bno){
		log.info("get Reply List of a Board"+bno);
		return mapper.getListWithPaging(cri, bno);
	}
	
}
