package com.codehows.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import com.codehows.domain.BoardVO;

public interface BoardMapper {
	//@Select("select * from tbl_board where bno > 0")
	public List<BoardVO> getList();
}
