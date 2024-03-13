package com.codehows.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.codehows.domain.BoardVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTests {
	@Setter(onMethod_ = {@Autowired})
	private BoardService service;
	
//	@Test
//	public void testExist() {
//		log.info(service);
//		assertNotNull(service);
//	}
	
//	@Test
//	public void testRegister() {
//		BoardVO board = new BoardVO();
//		board.setTitle("새로 추가하는 글321");
//		board.setContent("새로 추가하는 내용321");
//		board.setWriter("newbie");
//		
//		service.register(board);
//		log.info("생성된 게시물의 번호: "+board.getBno());
//		
//	}
	
//	@Test
//	public void testGetList() {
//		//forEach로 board에 있는 개체를 하나씩 log.info에 집어넣기
//		service.getList().forEach(board -> log.info(board));
//	}
	
	//데이터베이스 테이블의 첫번째 행 출력
//	@Test
//	public void testGet() {
//		log.info(service.get(1L));
//	}
	
	// 특정 게시물 삭제
//	@Test
//	public void testDelete() {
//		log.info("REMOVE RESULT: "+service.remove(2L));
//	}
	
	//특정 게시물 수정
	@Test
	public void testUpdate() {
		BoardVO board = service.get(1L);
		if(board == null) {
			return;
		}
		board.setTitle("제목을 수정합니다87");
		log.info("Modify Result: " + service.modify(board));
	}
}
