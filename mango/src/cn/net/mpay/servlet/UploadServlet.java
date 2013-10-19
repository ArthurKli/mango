package cn.net.mpay.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sun.misc.BASE64Decoder;
import cn.net.mpay.dao.MbDao;
import cn.net.mpay.util.ReturnConst;

public class UploadServlet extends HttpServlet {

	private final Log log = LogFactory.getLog(getClass());
	

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			 {
	    String BASEPATH =this.getServletConfig().getServletContext().getRealPath("");
		String uploadType=request.getParameter("type");

		
		if ("photo".equals(uploadType)) {
			String mid=request.getParameter("id");
			String savePath = BASEPATH+"/photo/"+mid+"/";

			File f1 = new File(savePath);
			if (!f1.exists()) {
				f1.mkdirs();
			}
			int fLen=f1.listFiles().length;
			log.info("文件总数："+fLen);
			if (fLen>=10) {
				log.error("The total number of limit exceeded");
		        try {
		        	response.setCharacterEncoding("UTF-8");
					response.getWriter().print(ReturnConst.ERROR);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return ;
			}
			DiskFileItemFactory fac = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(fac);
			upload.setHeaderEncoding("utf-8");
			List fileList = null;
			try {
				fileList = upload.parseRequest(request);
			} catch (FileUploadException ex) {
				return;
			}
			Iterator<FileItem> it = fileList.iterator();
			String name = "";   //图片名称
			String imgType = ""; //图片后缀
			while (it.hasNext()) {
				FileItem item = it.next();
				if (!item.isFormField()) {
					name = item.getName();
					long size = item.getSize();
					String type = item.getContentType();
					log.info(size + " " + type);
					if (name == null || name.trim().equals("")) {
						continue;
					}
					if (name.lastIndexOf(".") >= 0) {
						imgType = name.substring(name.lastIndexOf("."));
					}
					File file = null;
					int i =0;
					do {
						if (i>9) { //个人相册默认只能存十张相片
							i=0;
						}
						i++;
						file = new File(savePath + i + imgType);
						
					} while (file.exists());
					File saveFile = new File(savePath + i + imgType);
					log.info("图："+i + imgType);
					try {
						item.write(saveFile);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			try {
				response.getWriter().print(ReturnConst.SUCCESS);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");        
		MbDao mbDao = (MbDao)context.getBean("mbDao");
		String data = "上传成功";
		String filePath=BASEPATH+"/avatar/";
		File f1 = new File(filePath);
		if (!f1.exists()) {
			f1.mkdirs();
		}
		   String ajaxUpdateResult = "";
	        try {
	            List<FileItem> fItems = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);  
	    		String name = "";   //图片名称
	    		String imgType = ""; //图片后缀
	            for (FileItem item : fItems) {
	                if (item.isFormField()) {
	                	if("sampleFile".equals(item.getFieldName())){
	                    String[] part =item.getString().split(",");
	                    imgType =findImageType(part[0]);
	                    File file = null;
	    				do {
	    					// 生成文件名：
	    					name = UUID.randomUUID().toString();
	    					file = new File(filePath + name + imgType);
	    				} while (file.exists());
	    				GenerateImage(part[1],filePath + name + imgType);
	                    mbDao.setMbAvatar(1, name+imgType);
	                    }
	                } else {
	                    String fileName = item.getName();
	                    InputStream content = item.getInputStream();
	                    response.setContentType("text/plain");
	                    response.setCharacterEncoding("UTF-8");
	                }
	            }
	        } catch (Exception e) {
	            log.error("Parsing file upload failed.", e);
	            data="上传失败！";
	        }
	        try {
	        	response.setCharacterEncoding("UTF-8");
				response.getWriter().print(data);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
	}
	public String findImageType(String temp){
		if (temp.contains("image/bmp")) {
			return ".bmp";
		}else if (temp.contains("image/gif")) {
			return ".gif";
		}else if (temp.contains("image/png")) {
			return ".png";
		}else if (temp.contains("image/bmp")) {
			return ".bmp";
		}else if (temp.contains("image/jpeg")) {
			return ".jpg";
		}else {
			return ".jpg";
		}
	}
	public static boolean GenerateImage(String imgStr, String imgFilePath) {// 对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) // 图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] bytes = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {// 调整异常数据
                    bytes[i] += 256;
                }
            }
            // 生成jpeg图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(bytes);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
