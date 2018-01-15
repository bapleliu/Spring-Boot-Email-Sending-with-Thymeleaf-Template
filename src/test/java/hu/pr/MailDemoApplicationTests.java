package hu.pr;

import hu.pr.mailservice.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailDemoApplicationTests {

	@Autowired
	private MailService mailService;
	private static final String[] RECIPIENTS = {"874822608@qq.com"};
	private static final String SUBJECT = "proba";

	@Test
	public void contextLoads() {
	}

	@Test
	public void sendNormalTextMessage() {
		try {
			mailService.sendMailText(RECIPIENTS, SUBJECT, "Ez egy lev�l t�rzs", new String[] {"proba.txt"});
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}

	@Test
	public void sendHtmlTemplateMessage() {
		String[] messages = { "Ez egy �zenet�������", "Valami", "m�sik", "Harmadik" };
		Map<String, Object> datas = new HashMap<>();
		datas.put("messages", messages);
		try {
			mailService.sendMail(RECIPIENTS, SUBJECT, "MailTemplate1", datas, new String[] {"proba.txt"});
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

}
