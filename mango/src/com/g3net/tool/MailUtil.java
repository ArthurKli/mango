package com.g3net.tool;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.apache.log4j.Logger;


/**
 * 邮件收发工具类
 * @author yuxiaowei
 *
 */
public class MailUtil {

	private static Logger log=Logger.getLogger(MailUtil.class);
	public static void send(String smtp,String title,String content,String filename,byte[] attach,
			final String from,String to,final String password)
	{
		send(smtp, title, content, filename, attach, from, to, password,"text/plain;charset=utf-8");
	}
	/**
	 * 
	 * @param smtp		邮件服务器
	 * @param title		邮件标题
	 * @param content	邮件内容
	 * @param filename	附件名
	 * @param attach	附件数据 byte[]
	 * @param from		发件人
	 * @param to		收件人，多人以,分隔
	 * @param password	发件人邮箱密码
	 */
	public static void send(String smtp,String title,String content,String filename,byte[] attach,
			final String from,String to,final String password,String type)
	{
		System.setProperty("mail.mime.encodefilename", "true");
		// 创建Properties 对象   
		Properties props = System.getProperties();

		// 添加smtp服务器属性   
		props.put("mail.smtp.host", smtp);
		props.put("mail.smtp.auth", "true"); //163的stmp不是免费的也不公用的，需要验证   
		// 创建邮件会话   
		Session session = Session.getDefaultInstance(props, new Authenticator()
		{ //验账账户   
					@Override
					public PasswordAuthentication getPasswordAuthentication()
					{
						return new PasswordAuthentication(from, password);
					}
		});
		try
		{
			String[] tos=to.split(",");
			MimeMultipart mcon=new MimeMultipart();   //创建邮件体对象
			
		    if(mcon==null) return ; 
		    MimeBodyPart part=new MimeBodyPart();        //创建文本部分对象
		    part.setText(content,"utf-8"); 
		    part.setHeader("Content-Type",type); //
		    mcon.addBodyPart(part); 
		   
			//设置抄送收件人				
			//message.addRecipients(Message.RecipientType.CC,new InternetAddress()); 
			//设置暗抄送人				
			//message.addRecipients(Message.RecipientType.BCC,new InternetAddress()); 
		    
		    if(StringUtils.hasText(filename)&&attach!=null&&attach.length>0)
		    {
			    try {
			    	  //设置附件
				    part=new MimeBodyPart();//创建MIME对象
				    DataSource fds = new ByteArrayDataSource(attach,"application/octet-stream"); //创建文件流对象
				    part.setDataHandler(new DataHandler(fds)); 
					part.setFileName(filename);
					mcon.addBodyPart(part);        //添加二进制编码至邮件体中
				} catch (Exception e) {
					// TODO Auto-generated catch block
					log.error("",e);
				} 
		    }
			for(int i=0;i<tos.length;i++)
			{
				//定义邮件信息   
				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress(from));
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(tos[i]));
				message.setSubject(title,"utf-8");
//				message.setText(content);
				 message.setContent(mcon); //添加文本至邮件中   
				// 发送消息   
				//session.getTransport("smtp").send(message);  //也可以这样创建Transport对象   
				Transport.send(message);
			}
		} catch (Exception e)
		{
			log.error("",e);
		}
	}
	public static void main(String[] args) {
		String host = "mail.3g.net.cn"; // 指定的smtp服务器   
		String from = "yuxiaowei@3g.net.cn"; // 邮件发送人的邮件地址   
		String to = "yuxiaowei@3g.net.cn"; // 邮件接收人的邮件地址   
		final String password = "160376"; //发件人的邮件密码   
		
		String title="测试邮件";
		String content="测试邮件";
		String filename="测试邮件.csv";
		try {
			send(host,title,content,filename,content.getBytes("gbk"),from,to,password);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		try {
//			log.info(new String(filename.getBytes("gbk"),"gbk"));
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	}

