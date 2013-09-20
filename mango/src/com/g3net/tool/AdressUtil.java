package com.g3net.tool;

public class AdressUtil {
	
   public static String[] pro={"河北","山西","吉林","黑龙江","江苏","浙江","安徽","江西","山东","河南","湖北","湖南","广东","四川","贵州","云南","陕西","甘肃","青海","陕西","甘肃"};

	public static String getProvince(String address){
		for (int i = 0; i < pro.length; i++) {
			if (address.indexOf(pro[i])>-1&&address.indexOf(pro[i])<2) {
				System.out.println(address.indexOf(pro[i]));
				return pro[i]+"省";
			}
		}
		return "";
	}
	
	public static String getCity(String address){
		int start=address.indexOf("省");
		int end=address.indexOf("市");
		int next=address.indexOf("县");
		int proPosition=getProvincePosition(address);
		if (start!=-1&&end!=-1) {
			return address.substring(start+1,end)+"市";
		}else if(start!=-1&&next!=-1){
			return address.substring(start+1,next)+"县";
		}else if(proPosition!=-1&&end!=-1){
//			return address.substring(proPosition+1,end)+"市";
		}else if(proPosition==-1&&end!=-1){
			return address.substring(0,end)+"市";
		}
		
		
		return "";
	}
	
	
	public static int getProvincePosition(String address){
		for (int i = 0; i < pro.length; i++) {
			int a=address.indexOf(pro[i]);
			if (address.indexOf(pro[i])>-1&&address.indexOf(pro[i])<2) {
				return a;
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		String abc =getCity("广东省澄海县海珠区鹭江西家88号");
		System.out.println(abc);
		String b=getProvince("广东省广州市xx县海珠区鹭江西家88号");
		System.out.println(b);
	}


}
