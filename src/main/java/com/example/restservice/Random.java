package com.example.restservice;

public class Random {

	private final long id;
	private final String content;
	//private final int num;
	//private final String side;

	public Random(long id, String content) {
		this.id = id;
		this.content = content;
		//this.num=number;
	}

	public long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}
}



