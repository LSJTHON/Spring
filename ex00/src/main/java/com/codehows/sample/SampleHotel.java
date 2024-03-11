package com.codehows.sample;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.ToString;

@Component
@ToString
@Getter
public class SampleHotel {
	private Chef chef;
	public SampleHotel(Chef chef) { //생성자에서 Chef 지정 hotel - chef 의존성 주입
		this.chef = chef;
	}
}
