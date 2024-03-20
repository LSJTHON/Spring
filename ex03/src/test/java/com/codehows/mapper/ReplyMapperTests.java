package com.codehows.mapper;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.codehows.domain.Criteria;
import com.codehows.domain.ReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {
	private Long[] bnoArr = { 13L,13L,12L,11L,10L,9L,8L,7L,6L };
	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
	
	@Test
	public void testList() {
		Criteria cri = new Criteria();
		List<ReplyVO> replies = mapper.getListWithPaging(cri, bnoArr[0]);
		replies.forEach(reply -> log.info(reply));
	}
	
//	@Test
//	public void testUpdate() {
//		Long targetRno = 10L;
//		
//		ReplyVO vo = mapper.read(targetRno);
//		vo.setReply("update reply");
//		
//		int count = mapper.update(vo);
//		
//		log.info("update count" + count);
//	}
	
	
//	@Test
//	public void testDelete() {
//		Long targetRno = 1L;
//		
//		mapper.delete(targetRno);
//	}
	
	
//	@Test
//	public void testRead() {
//		Long targetRno = 10L;
//		
//		ReplyVO vo = mapper.read(targetRno);
//		
//		log.info(vo);
//	}
	
	
//	@Test
//	public void tesCreate() {
//		IntStream.rangeClosed(1,10).forEach(i -> {
//			ReplyVO vo = new ReplyVO();
//			
//			vo.setBno(bnoArr[1%5]);
//			vo.setReply("댓글 테스트"+i);
//			vo.setReplyer("replyer"+i);
//			
//			mapper.insert(vo);
//		});
//	}
//	
//	@Test
//	public void testMapper() {
//		log.info(mapper);
//	}
	
	
//	@Test
//	public void testMapper() {
//		log.info(mapper);
//	}
	
	
//	@Test
//	public void testList2() {
//		Criteria cri = new Criteria(2,10);
//		List<ReplyVO> replies = mapper.getListWithPaging(cri, 13L);
//		
//		replies.forEach(i -> log.info(i));
//	}
	
	
}
