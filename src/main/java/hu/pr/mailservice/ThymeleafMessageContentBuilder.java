package hu.pr.mailservice;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
public class ThymeleafMessageContentBuilder implements MessageContentBuilder {

	@Autowired
	private TemplateEngine templateEngine;

	@Override
	public String buildMessage(String templateName, Map<String, Object> datas) {
		Context context = new Context();
		for (Map.Entry<String, Object> entry : datas.entrySet()) {
			context.setVariable(entry.getKey(), entry.getValue());			
		}
		return templateEngine.process(templateName, context);
	}

}
