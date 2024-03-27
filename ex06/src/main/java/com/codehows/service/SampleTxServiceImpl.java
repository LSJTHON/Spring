package com.codehows.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codehows.mapper.Sample1Mapper;
import com.codehows.mapper.Sample2Mapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class SampleTxServiceImpl implements SampleTxService{
	@Setter(onMethod_ = @Autowired)
	private Sample1Mapper mapper1;
	
	@Setter(onMethod_ = @Autowired)
	private Sample2Mapper mapper2;
	
	@Transactional //한놈이 실패하면 둘다 안들어감
	@Override
	public void addData(String value) {
		log.info("mapper1.................");
		mapper1.insertCol1(value);
		
		log.info("mapper2.................");
		mapper2.insertCol2(value);
		
		log.info("end.....................");
	}
	
}
