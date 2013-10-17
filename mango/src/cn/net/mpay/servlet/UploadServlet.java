package cn.net.mpay.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

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

import cn.net.mpay.dao.MbDao;

public class UploadServlet extends HttpServlet {

	private final Log log = LogFactory.getLog(getClass());
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			 {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");        
		MbDao mbDao = (MbDao)context.getBean("mbDao");
		String data = "上传成功";
		   String ajaxUpdateResult = "";
	        try {
	            List<FileItem> fItems = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);            
	            for (FileItem item : fItems) {
	                if (item.isFormField()) {
	                    ajaxUpdateResult += "Field " + item.getFieldName() + 
	                    " with value: " + item.getString() + " is successfully read\n\r";
	                    
	                    if("sampleFile".equals(item.getFieldName())){
	                    	mbDao.setMbAvatar(1, item.getString().getBytes());
	                    }
	                    

	                } else {

	                    String fileName = item.getName();

	                    InputStream content = item.getInputStream();

	                    response.setContentType("text/plain");

	                    response.setCharacterEncoding("UTF-8");

	                    // Do whatever with the content InputStream.

	                    System.out.println(Streams.asString(content));

	                    ajaxUpdateResult += "File " + fileName + " is successfully uploaded\n\r";

	                }

	            }

	        } catch (Exception e) {
	            log.error("Parsing file upload failed.", e);
	            data="上传失败！";
	        }
	        System.out.println(ajaxUpdateResult);
	        try {
	        	response.setCharacterEncoding("UTF-8");
				response.getWriter().print(data);
			} catch (IOException e) {
				e.printStackTrace();
			}

		
	
	}

}
