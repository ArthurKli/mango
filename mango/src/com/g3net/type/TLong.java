package com.g3net.type;

public class TLong {
	private long value;


	public TLong(){
		
	}
	public TLong(long i){
		this.value=i;
	}
	
	public long getValue() {
		return value;
	}
	public void setValue(long value) {
		this.value = value;
	}
	public String toString(){
		return value+"";
	}
}
