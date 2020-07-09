package com.exercisetracker.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.exercisetracker.domain.Program;
import com.exercisetracker.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String sender;

	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	private JavaMailSender JavaMailSender;

	@Override
	public void sendProgramConfirmationEmail(Program obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromProgram(obj);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromProgram(Program obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getUser().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Programa criado! Código: " + obj.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		return sm;
	}

	protected String htmlFromTemplateProgram(Program obj) {
		Context context = new Context();
		context.setVariable("program", obj);
		return templateEngine.process("email/confirmacaoProgram", context);
	}

	@Override
	public void sendProgramConfirmationHtmlEmail(Program obj) {
		try {
			MimeMessage mm = prepareMimeMessageFromProgram(obj);
			sendHtmlEmail(mm);
		} catch (MessagingException e) {
			sendProgramConfirmationEmail(obj);
		}
	}

	protected MimeMessage prepareMimeMessageFromProgram(Program obj) throws MessagingException {
		MimeMessage mimeMessage = JavaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
		mmh.setTo(obj.getUser().getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Programa Criado! Código: " + obj.getId());
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplateProgram(obj), true);
		return mimeMessage;
	}

	@Override
	public void sendNewPasswordEmail(User user, String newPass) {
		SimpleMailMessage sm = prepareNewPasswordEmail(user, newPass);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareNewPasswordEmail(User user, String newPass) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(user.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Solicitação de nova senha");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("Nova senha: " + newPass);
		return sm;
	}
}