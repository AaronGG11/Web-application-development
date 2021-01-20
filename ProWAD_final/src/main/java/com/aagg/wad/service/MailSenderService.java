package com.aagg.wad.service;

import com.aagg.wad.model.MailBodyContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
public class MailSenderService {
	@Autowired
	private JavaMailSender sender;

	@Autowired
	private TemplateEngine templateEngine;

	public String sendEmail(String to, String subject, MultipartFile image, InputStreamSource imageSource)
			throws Exception {
		String templateName = "mail/myTemplate";
		Context context = new Context();
		context.setVariable("Content", create());
		// add for image
		context.setVariable("imageResourceName", image.getName());
		String body = templateEngine.process(templateName, context);

		MimeMessage mail = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mail, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
				StandardCharsets.UTF_8.name());

		// added for attachment
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(body, true);
		// ad
		helper.addInline(image.getName(), imageSource, image.getContentType());
		helper.setFrom("basant1993.dev@gmail.com");
		sender.send(mail);
		return "mail send successfully";
	}

	public MailBodyContent create() {
		MailBodyContent content = new MailBodyContent();
		content.setUsername("usuario");
		List<String> technology = new ArrayList<>();
		technology.add("Spring-Boot");
		technology.add("Thymeleaf");
		technology.add("Template Engine");
		content.setTechnology(technology);
		content.setMessage("Este es un correo de bienvenida al sistema desarrollado en la optativa Web Application Development en IPN ESCOM 2021.");
		content.setTechnology(technology);
		return content;
	}

}
