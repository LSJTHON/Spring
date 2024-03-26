package com.codehows.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.codehows.domain.Criteria;
import com.codehows.domain.ReplyVO;

public interface ReplyMapper {
	public int insert(ReplyVO vo);
	
	public ReplyVO read(Long rno); // 특정 댓글 읽기
	
	public int delete(Long rno);
	
	public int update(ReplyVO reply);
	
	public List<ReplyVO> getListWithPaging(
			@Param("cri") Criteria cri,
			@Param("bno") Long bno
			);	
	public int getCountByBno(Long bno);
	}

