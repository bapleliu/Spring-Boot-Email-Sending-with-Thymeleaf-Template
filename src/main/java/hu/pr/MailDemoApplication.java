package hu.pr;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.activation.FileTypeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import hu.pr.mailservice.MailService;

@SpringBootApplication
public class MailDemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MailDemoApplication.class, args);
	}

	@Autowired
	private MailService mailService;
	
	private static final String[] RECIPIENTS = {"874822608@qq.com"};
	private static final String SUBJECT = "proba";
	
	@Override
	public void run(String... args) throws Exception {
//		sendNormalTextMessage();
//		sendHtmlTemplateMessage();
		sendHtmlTemplateMessageWithInlineImage_imageFileName();
//		sendHtmlTemplateMessageWithInlineImage_imageByteArray();
	}
	
	private void sendNormalTextMessage() {
		try {
			mailService.sendMailText(RECIPIENTS, SUBJECT, "Ez egy lev�l t�rzs", new String[] {"proba.txt"});
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		
	}
	
	private void sendHtmlTemplateMessage() {
		String[] messages = { "Ez egy �zenet�������", "Valami", "m�sik", "Harmadik" };
		Map<String, Object> datas = new HashMap<>();
		datas.put("messages", messages);
		try {
			mailService.sendMail(RECIPIENTS, SUBJECT, "MailTemplate1", datas, new String[] {"proba.txt"});
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}		
	}
	
	private void sendHtmlTemplateMessageWithInlineImage_imageFileName() {
		String[] messages = { "Ez egy �zenet�������", "Valami", "m�sik", "Harmadik" };
		Path p = Paths.get("proba.jpg");		
		
		Map<String, Object> datas = new HashMap<>();
		datas.put("messages", messages);
		datas.put("imageResourceName", p.getFileName().toString());
		try {
			mailService.sendMailWithInlineImage(RECIPIENTS, SUBJECT, "MailTemplate2", datas, new String[] {"proba.txt"}, p.getFileName().toString(), "proba.jpg");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}		
	}
	
	private void sendHtmlTemplateMessageWithInlineImage_imageByteArray() throws Exception {
		String[] messages = { "Ez egy �zenet�������", "Valami", "m�sik", "Harmadik" };
		Path p = Paths.get("proba.jpg");
		byte[] image = Files.readAllBytes(p);
		String contentType = FileTypeMap.getDefaultFileTypeMap().getContentType(p.toFile());

		Map<String, Object> datas = new HashMap<>();
		datas.put("messages", messages);
		datas.put("imageResourceName", p.getFileName().toString());
		try {
			mailService.sendMailWithInlineImage(RECIPIENTS, SUBJECT, "MailTemplate2", datas, new String[] {"proba.txt"}
				, p.getFileName().toString(), image, contentType);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}		
	}

}
