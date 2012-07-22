package com.pichler.mail;

import java.io.IOException;
import java.io.InputStream;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class SendMail {

	private static final String EMAIL = "eduardopichler@gmail.com";

	public static void main(String[] args) throws MailException,
			MessagingException, IOException {
		ClassPathXmlApplicationContext ct = new ClassPathXmlApplicationContext(
				new String[] { "appContext.xml" });
		JavaMailSender sender = (JavaMailSender) ct.getBean("mailSender");
		sender.send(getMessageSimples());
		sender.send(getMessageComAnexo());
		sender.send(getMessageComInlineResources());
		System.out.println("enviou");
	}

	private static MimeMessage getMessageComAnexo() throws MessagingException {
		MimeMessage message = new JavaMailSenderImpl().createMimeMessage();
		// use the true flag to indicate you need a multipart message
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setTo(EMAIL);
		helper.setText("Check out this image!");
		helper.setSubject("Segue anexo...");

		// let's attach the infamous windows Sample file (this time copied to
		// c:/)
		// FileSystemResource file = new FileSystemResource("c:/Sample.jpg"));
		InputStreamSource file = new ByteArrayResource("texto".getBytes());

		helper.addAttachment("anexo.txt", file);

		return message;
	}

	private static MimeMessage getMessageComInlineResources()
			throws MessagingException, IOException {
		MimeMessage message = new JavaMailSenderImpl().createMimeMessage();
		// use the true flag to indicate you need a multipart message
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setTo(EMAIL);
		// use the true flag to indicate the text included is HTML
		helper.setSubject("Segue imagem...");
		final String imagemSurf = "surf.jpg";
		// let's include the infamous windows Sample file (this time copied to
		// c:/)
		InputStream resourceAsStream = SendMail.class
				.getResourceAsStream(imagemSurf);
		byte bytes[] = new byte[resourceAsStream.available()];
		resourceAsStream.read(bytes, 0, bytes.length);

		ByteArrayResource res = new ByteArrayResource(bytes) {
			@Override
			public String getFilename() {

				return imagemSurf;
			}
		};
		// To work... first add the text, and after the resource!
		helper.setText(
				"<html><body><img src='cid:identifier1234'></body></html>",
				true);
		helper.addInline("identifier1234", res);

		return message;
	}

	private static SimpleMailMessage getMessageSimples() {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(EMAIL);
		mail.setFrom("alguem@gmail.com");
		mail.setSubject("Sem anexo...");
		mail.setText("conteudo");
		return mail;
	}

}
