package com.codehows.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Aspect
@Log4j
@Component
public class LogAdvice {
	@Before("execution(* org.codehows.service.SampleService*.*(..))")
	public void logBefore() {
		log.info("=========================");//SampleService 실행전 실행
	}
}
