package com.codehows.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.codehows.domain.BoardVO;
import com.codehows.domain.Criteria;

public interface BoardMapper {
	//@Select("select * from tbl_board where bno > 0")
	public List<BoardVO> getList();
	
	public List<BoardVO> getListWithPaging(Criteria cri);
	
	public void insert(BoardVO board); //pk값을 알 필요가 없는 경우
	
	public void insertSelectKey(BoardVO board); // pk값을 알아야 하는 경우
	
	public BoardVO read(Long bno);
	
	public int delete(Long bno);
	
	public int update(BoardVO board);
	
	public int getTotalCount(Criteria cri);
	public void updateReplyCnt(@Param("bno") Long bno, @Param("amount") int amount);

	//게시물 삭제를 위한 댓글 삭제 댓글이 있으면 기본키의 제약조건 때문에 삭제 불가능
	public void deleteResult(Long bno);
}
