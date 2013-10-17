package cn.net.mpay.action;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
@Component
@Scope("prototype")
public class UploadAction extends ActionSupport{	 
	
	
	
	private String msg;
	private File uploadFile; 
	private String uploadFileFileName; 
	private String uploadFileContentType; 
	
	private final Log log = LogFactory.getLog(getClass());
	
	public String upload() throws IOException, ServletException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String ajaxUpdateResult = "";

        try {

            List<FileItem> fItems = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);            

            for (FileItem item : fItems) {

                if (item.isFormField()) {

                    ajaxUpdateResult += "Field " + item.getFieldName() + 

                    " with value: " + item.getString() + " is successfully read\n\r";

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

        } catch (FileUploadException e) {

            throw new ServletException("Parsing file upload failed.", e);

        }
        System.out.println(ajaxUpdateResult);

        response.getWriter().print(ajaxUpdateResult);		
		
		return SUCCESS;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getUploadFileFileName() {
		return uploadFileFileName;
	}

	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}

	public String getUploadFileContentType() {
		return uploadFileContentType;
	}

	public void setUploadFileContentType(String uploadFileContentType) {
		this.uploadFileContentType = uploadFileContentType;
	}
	


	
	

}
