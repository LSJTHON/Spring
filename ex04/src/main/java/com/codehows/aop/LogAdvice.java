package com.codehows.aop;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Aspect
@Log4j
@Component
public class LogAdvice {
	@Before("execution(* com.codehows.service.SampleService*.*(..))")
	public void logBefore() {
		log.info("=========================");//SampleService 실행전 실행
	}
	
	@Before("execution(* com.codehows.service.SampleService*.doAdd(String,"
			+ "String)) && args(str1, str2)")
	public void logBeforeWithParam(String str1, String str2) {
		log.info("str1: " +str1);
		log.info("str2: " +str2);
	}
	@AfterThrowing(pointcut = "execution(* com.codehows.service.SampleService*.*(..))",
			throwing = "exception")
	public void logExeption(Exception exception) {
		log.info("Exception!!!!!!!!!!");
		log.info("exception: "+exception);
	}
}
