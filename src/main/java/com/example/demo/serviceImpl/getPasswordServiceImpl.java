package com.example.demo.serviceImpl;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import com.example.demo.bean.MailRequest;
import com.example.demo.bean.MailResponse;
import com.example.demo.repo.IUserRepo;
import com.example.demo.repo.IUserTempRepo;
import com.example.demo.service.IGetPasswordService;

@Service
public class getPasswordServiceImpl implements IGetPasswordService {

	@Autowired
	private JavaMailSender sender;

	@Autowired
	private IUserRepo userRepo;

	@Autowired
	private IUserTempRepo tempRepo;

	@Override
	public MailResponse sendEmail(MailRequest request, Map<String, Object> model) {

		MailResponse response = new MailResponse();
		MimeMessage message = sender.createMimeMessage();
		try {
			// set mediaType
			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());
			// add attachment
			// helper.addAttachment("logo.png", new ClassPathResource("logo.png"));

			// Template t = config.getTemplate("email-template.ftl");
			// String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

			String userName = userRepo.findName(request.getTo());
			String password = tempRepo.findPasswordUsingEmail(request.getTo());

			helper.setTo(request.getTo());
			helper.setText("Hi " + userName + "," + " Your Account password is " + password, true);
			helper.setSubject("View: Account Password for Your Social Media API Password");
			helper.setFrom("");
			sender.send(message);

			response.setMessage("mail send to : " + request.getTo());
			response.setStatus(Boolean.TRUE);

		} catch (MessagingException e) {
			response.setMessage("Mail Sending failure : " + e.getMessage());
			response.setStatus(Boolean.FALSE);
		}

		return response;
	}

}
