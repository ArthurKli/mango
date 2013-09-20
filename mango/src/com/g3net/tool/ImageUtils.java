package com.g3net.tool;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;

import org.apache.log4j.Logger;

public class ImageUtils {
	private static final Logger log = Logger.getLogger(ImageUtils.class);
	
	/**
	 * 对图片进行缩放
	 * @param imagebyte  原图的字节数组 
	 * @param width 需要缩放的宽度
	 * @param height 需要缩放的高度
	 * @param algor 缩放使用的算法，见Image的jdk文档，如，Image.SCALE_SMOOTH缩放的质量比较好，但效率比较低
	 * @return 返回缩放后的图片字节数组
	 */
	public static byte[] scaleImage(byte[] imagebyte,int width,int height,int algor) 
	{
		if(imagebyte==null) return null;
		
		FileImageOutputStream   fios=null;//保存原图的图片
		Image image =null;//读取原图
		ByteArrayOutputStream out=null;
		BufferedImage newImage=null;
	//	JPEGImageEncoder encoder=null;
		try
		{
			image = ImageIO.read(new ByteArrayInputStream(imagebyte));//读取原图
			//根据传过去得参数 ---变成 3个字符串数组 
			newImage= new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);  
			//绘制缩小后的图   
			newImage.getGraphics().drawImage(image.getScaledInstance(width, 
					height, algor), 0, 0, null);
			out=new ByteArrayOutputStream(); 
			//encoder=JPEGCodec.createJPEGEncoder(out);   
			//encoder.encode(newImage);    
			ImageIO.write(newImage, "jpeg", out);
			
			
			
		}
		catch(Exception e)
		{
			log.error("图片缩放！",e);
			throw new  IllegalStateException("图片缩放！",e);
		}
		finally
		{			
			try{
				if(fios!=null) fios.close();
				
				if(image!=null) image=null;
				
				if(out!=null) {
					out.flush();
					out.close();
				}
				//if(encoder!=null) encoder=null;
				
			}catch(Exception e) {}
		}
		return out.toByteArray();



	}
	// 从文件中读取图片--转换成byte数组
	public static byte[] getPicByte(String picPath) throws Exception {
		byte[] out=null;
		try{
			out= FileUtils.readBytes(picPath);
		}catch(Exception e){
			log.error("picPath="+picPath, e);
			return null;
		}
		return out;

	}
}
