package com.g3net.tool;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CnUpperCaser {// 日期转化为大小写
	public static String dataToUpper(String dateStr) {
		String res="";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = df.parse(dateStr);
		} catch (Exception e) {
			// 日期型字符串格式错误
			System.out.println("日期型字符串格式错误");
		}
		if(date!=null){
			Calendar ca = Calendar.getInstance();
			ca.setTime(date);
			int year = ca.get(Calendar.YEAR);
			int month = ca.get(Calendar.MONTH) + 1;
			int day = ca.get(Calendar.DAY_OF_MONTH);
			res=numToUpper(year) + "年" + monthToUppder(month) + "月"+dayToUppder(day) + "日";
		}
		return res;
	}

	// 将数字转化为大写
	public static String numToUpper(int num) {
		 String u[] = {"零","壹","贰","叁","肆","伍","陆","柒","捌","玖"};
//		String u[] = { "〇", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
		char[] str = String.valueOf(num).toCharArray();
		String rstr = "";
		for (int i = 0; i < str.length; i++) {
			rstr = rstr + u[Integer.parseInt(str[i] + "")];
		}
		return rstr;
	}

	// 月转化为大写
	public static String monthToUppder(int month) {
		if (month < 10) {
			return numToUpper(month);
		} else if (month == 10) {
			return "十";
		} else {
			return "十" + numToUpper(month - 10);
		}
	}

	// 日转化为大写
	public static String dayToUppder(int day) {
		if (day < 20) {
			return monthToUppder(day);
		} else {
			char[] str = String.valueOf(day).toCharArray();
			if (str[1] == '0') {
				return numToUpper(Integer.parseInt(str[0] + "")) + "十";
			} else {
				return numToUpper(Integer.parseInt(str[0] + "")) + "十"
						+ numToUpper(Integer.parseInt(str[1] + ""));
			}
		}
	}
	public   static  String changeToBig( double  value){
        char [] hunit = { '拾' , '佰' , '仟' };                                   // 段内位置表示 
        char [] vunit = { '万' , '亿' };                                        // 段名表示 
        char [] digit = { '零' , '壹' , '贰' , '叁' , '肆' , '伍' , '陆' , '柒' , '捌' , '玖' };   // 数字表示 
        long  midVal  =  ( long )(value * 100 );                                // 转化成整形 
       String valStr = String.valueOf(midVal);                           // 转化成字符串 
       String head = valStr.substring( 0 ,valStr.length() - 2 );              // 取整数部分 
       String rail = valStr.substring(valStr.length() - 2 );                // 取小数部分 

       String prefix = "" ;                                               // 整数部分转化的结果 
       String suffix = "" ;                                               // 小数部分转化的结果
        // 处理小数点后面的数 
        if (rail.equals( "00" )){                                          // 如果小数部分为0 
         suffix = " 整 " ;
       }  else {
         suffix = digit[rail.charAt( 0 ) - '0' ] + "角" + digit[rail.charAt( 1 ) - '0' ] + "分" ;  // 否则把角分转化出来 
       }
        // 处理小数点前面的数 
        char [] chDig = head.toCharArray();                  // 把整数部分转化成字符数组 
        char  zero = '0' ;                                    // 标志'0'表示出现过0 
        byte  zeroSerNum  =   0 ;                              // 连续出现0的次数 
        for ( int  i = 0 ;i < chDig.length;i ++ ){                  // 循环处理每个数字 
          int  idx = (chDig.length - i - 1 ) % 4 ;                   // 取段内位置 
          int  vidx = (chDig.length - i - 1 ) / 4 ;                  // 取段位置 
          if (chDig[i] == '0' ){                              // 如果当前字符是0 
           zeroSerNum ++ ;                                 // 连续0次数递增 
            if (zero  ==   '0' ){                               // 标志 
             zero = digit[ 0 ];
           }  else   if (idx == 0   &&  vidx  > 0   && zeroSerNum  <   4 ){
             prefix  +=  vunit[vidx - 1 ];
             zero = '0' ;
           }
            continue ;
         }
         zeroSerNum  =   0 ;                                 // 连续0次数清零 
          if (zero  !=   '0' ) {                               // 如果标志不为0,则加上,例如万,亿什么的 
           prefix += zero;
           zero = '0' ;
         }
         prefix += digit[chDig[i] - '0' ];                   // 转化该数字表示 
          if (idx  >   0 ) prefix  +=  hunit[idx - 1 ];                  
          if (idx == 0   &&  vidx > 0 ){
           prefix += vunit[vidx - 1 ];                      // 段结束位置应该加上段名如万,亿 
         }
       }

        if (prefix.length()  >   0 ) prefix  +=   '圆' ;         // 如果整数部分存在,则有圆的字样 
        return  prefix + suffix;                          // 返回正确表示 
     } 
	
	public static void main(String[] args) {
		System.out.println(numToUpper(123));
		String str="123.12";
		
		System.out.println(changeToBig(Double.parseDouble(str)));
	}
	
	
	}


