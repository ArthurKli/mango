package com.g3net.type;

public class TDouble {

	private double value;


	public TDouble(){
		
	}
	public TDouble(double i){
		this.value=i;
	}
	
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public String toString(){
		return value+"";
	}
}
