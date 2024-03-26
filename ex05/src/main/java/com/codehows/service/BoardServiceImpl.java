package com.codehows.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codehows.domain.BoardAttachVO;
import com.codehows.domain.BoardVO;
import com.codehows.domain.Criteria;
import com.codehows.mapper.BoardAttachMapper;
import com.codehows.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class BoardServiceImpl implements BoardService {

	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	
	@Setter(onMethod_ = @Autowired)
	private BoardAttachMapper attachMapper;
	
	@Transactional
	@Override
	public void register(BoardVO board) { //인터페이스는 BoardService에 온다.
	
      log.info("register......" + board);

      
      //insertSelectKey는 BoardMapper.xml이랑 mybatis로 인해 연결되어 실행됨.
      //그리고 insertSelectKey는 mybatis안에서 bno값을 return 해줌.
      //mapper.insertSelectKey(board); 
      mapper.insertSelectKey(board);
      
      if(board.getAttachList() == null || board.getAttachList().size() <= 0) {
    	  return;
      }
      
      
      board.getAttachList().forEach(attach -> {
    	  attach.setBno(board.getBno());
    	  attachMapper.insert(attach);
      });
   }
	

   @Override
   public BoardVO get(Long bno) {

      log.info("get......" + bno);

      return mapper.read(bno);

   }
   @Transactional
   @Override
   public boolean modify(BoardVO board) {

      log.info("modify......" + board);
      
      attachMapper.deleteAll(board.getBno());
      
      boolean modifyResult = mapper.update(board) == 1;
      
      if(modifyResult && board.getAttachList() != null && board.getAttachList().size() > 0) {
    	  board.getAttachList().forEach(attach -> {
    		  attach.setBno(board.getBno());
    		  attachMapper.insert(attach);
    	  });
      }

      //return mapper.update(board) == 1;
      return modifyResult;
   }

   
   @Transactional
   @Override
   public boolean remove(Long bno) {
	   
	   log.info("remove...." + bno);
      
	   attachMapper.deleteAll(bno);
	   mapper.deleteResult(bno);
	   return mapper.delete(bno) == 1;
   }

   @Override
   public List<BoardVO> getList(Criteria cri) {

      log.info("getList..........: "+cri);

      return mapper.getListWithPaging(cri);
   }
   
   @Override
   public int getTotal(Criteria cri) {
	   log.info("get total count");
	   
	   return mapper.getTotalCount(cri);
   }
   
   @Override
   public List<BoardAttachVO> getAttachList(Long bno){
	   log.info("get Attach list by bno: "+bno);
	   return attachMapper.findByBno(bno);
   }
   
   

}
