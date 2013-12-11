
import java.io.*;
//import java.util.*;
/***
import javax.mail.*;
import javax.mail.Message.*;
import javax.mail.internet.*;
import javax.activation.*;
***/

import org.apache.commons.mail.*;

public class EmailSender {
	
	public EmailSender() {
		
	}
	
	public static void main (String[] args) {
		File f = new File("/home/tony/Share/2.html");
		String messageText = "";
		messageText = read.readfile(f);
		send(messageText);
	}
	
	public static void send(String message) {	
		//SimpleEmail email = new SimpleEmail();
		MultiPartEmail multipartemail = new MultiPartEmail();
		EmailAttachment attachment = new EmailAttachment();
		
		multipartemail.setTLS(true);
		multipartemail.setHostName("mail-relay.autodesk.com");
		//email.setAuthentication("tony.w.feng@gmail.com", "****");
		String mail_to_list[] = {"tony.w.feng@gmail.com","wei.feng@autodesk.com"};
		//String mail_to_list[] = {"wei.feng@autodesk.com","sandy.ma@autodesk.com","Jonathan.wu@autodesk.com"};
		String attachment_paths[] = {"/home/tony/Desktop/attachment1.txt","/home/tony/Desktop/attachment2.txt"};
		try {
						
			for (int i=0; i<mail_to_list.length; i++) {
				multipartemail.addTo(mail_to_list[i]);
			}
			
			
			for (int i=0; i<attachment_paths.length; i++) {
				attachment.setPath(attachment_paths[i]);
				multipartemail.attach(attachment);
			}
			
			
			multipartemail.addCc("wei.feng@autodesk.com");
			//email.addBcc("jonathan.wu@autodesk.com");
			multipartemail.setFrom("wei.feng@autodesk.com");
			
			multipartemail.setSubject("Hello guy~");
			multipartemail.setCharset("GB2312");
			//multipartemail.setMsg("test");
			multipartemail.setContent(message, "text/html;charset=gb2312");
			multipartemail.send();
		}
		catch (EmailException e) {
			e.printStackTrace();
		}
		
		System.out.println("success!");
		
		
		
	}

}


class read {
	
	public static String readfile(File file) {
		BufferedReader reader = null;
		String fileContent = "";
		String tempString = null;
		
		try {
			reader = new BufferedReader(new FileReader(file));
			while ((tempString = reader.readLine()) != null) {
				fileContent += tempString; 
			}
			reader.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (reader != null) {
				try {
					reader.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return fileContent;
		
	}
}