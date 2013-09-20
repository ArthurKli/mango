package com.g3net.type;

public class TBoolean {

	private boolean value;


	public TBoolean(){
		
	}
	public TBoolean(boolean i){
		this.value=i;
	}
	
	public boolean getValue() {
		return value;
	}
	public void setValue(boolean value) {
		this.value = value;
	}
	public String toString(){
		return value+"";
	}
}
