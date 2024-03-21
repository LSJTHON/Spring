package com.codehows.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
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
	@Around("execution(* com.codehows.service.SampleService*.*(..))")
	public Object logTime( ProceedingJoinPoint pjp) {
		long start = System.currentTimeMillis();
		
		log.info("Target: "+pjp.getTarget()); //메소드 객체
		log.info("Param: "+Arrays.toString(pjp.getArgs())); //메소드 매개변수
		
		Object result = null;
		try {
			result = pjp.proceed(); //메소드 실행 ->결과 값을 result 에 저장
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis(); //메서드 실행 후 시간 값 가져오기
		
		log.info("TIME: " +(end- start)); //메소드가 실행하는데 걸린 시간 구하기
		
		return result;
	}
}
