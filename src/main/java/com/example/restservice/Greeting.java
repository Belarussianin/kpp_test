package com.example.restservice;

public class Greeting {

	private final long id;
	private final String content;
	//private final int num;
	//private final String side;

	public Greeting(long id, String content) {
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
