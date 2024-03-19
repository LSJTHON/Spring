package com.codehows.mapper;

import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.codehows.domain.ReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {
	private Long[] bnoArr = { 15L,13L,12L,11L,10L,9L,8L,7L,6L };
	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
	
	@Test
	public void tesCreate() {
		IntStream.rangeClosed(1,10).forEach(i -> {
			ReplyVO vo = new ReplyVO();
			
			vo.setBno(bnoArr[1%5]);
			vo.setReply("댓글 테스트"+i);
			vo.setReplyer("replyer"+i);
			
			mapper.insert(vo);
		});
	}
	
	@Test
	public void testMapper() {
		log.info(mapper);
	}
	
	
//	@Test
//	public void testMapper() {
//		log.info(mapper);
//	}
}
