package com.aagg.wad.service;

import com.aagg.wad.model.MailBodyContent;
import com.aagg.wad.model.Person;
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
import java.util.stream.Collectors;

@Component
public class MailSenderService {
	@Autowired
	private JavaMailSender sender;

	@Autowired
	private TemplateEngine templateEngine;

	public String sendEmail(String to, String subject, MultipartFile image, InputStreamSource imageSource, Person user)
			throws Exception {
		String templateName = "mail/welcome";
		Context context = new Context();
		context.setVariable("Content", create(user));
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

	public MailBodyContent create(Person user) {
		MailBodyContent content = new MailBodyContent();
		content.setUsername("usuario");
		List<String> attributes = new ArrayList<>();
		attributes.add("Nombre usuario: " + user.getPersonName());
		attributes.add("Contraseña: : " + "No disponible por politicas de seguridad");
		attributes.add("Nombre: " + user.getName());
		attributes.add("Apellido: " + user.getLastName());
		attributes.add("Rol: " + user.getRoles().stream().collect(Collectors.toList()).get(0).getRole());

		content.setFeatures(attributes);
		content.setMessage("Este es un correo de bienvenida al sistema desarrollado en la optativa Web Application Development en IPN ESCOM 2021.");
		content.setFeatures(attributes);
		return content;
	}

}
