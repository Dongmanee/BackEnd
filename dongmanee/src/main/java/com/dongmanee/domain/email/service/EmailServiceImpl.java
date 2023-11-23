package com.dongmanee.domain.email.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.dongmanee.domain.email.exception.EmailSendingException;
import com.dongmanee.domain.email.exception.EmailVerifiedException;
import com.dongmanee.domain.email.utils.EmailRedisUtils;
import com.dongmanee.domain.member.dao.MemberRepository;
import com.dongmanee.domain.member.exception.DuplicateEmailException;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
	private final JavaMailSender emailSender;
	private final EmailRedisUtils emailRedis;
	private final MemberRepository memberRepository;

	@Value("${spring.mail.username}")
	private String email;

	@Value("${spring.mail.personal}")
	private String personal;

	@Value("${auth.code.length}")
	private int authCodeLength;

	@Value("${auth.code.expiration-millis}")
	private long authCodeExpirationMillis;

	/**
	 * 이메일 인증 코드 발송
	 *
	 * @param toEmail
	 */
	@Override
	public void sendSingUpEmailAuthCode(String toEmail) {
		if (memberRepository.existsByEmail(toEmail)) {
			throw new DuplicateEmailException("이미 가입한 이메일입니다.");
		}

		String title = "동만이 이메일 인증";
		String authCode = createAuthCode();

		MimeMessage emailForm = createAuthCodeEmailForm(toEmail, title, authCode);
		emailSender.send(emailForm);

		emailRedis.setData(toEmail, authCode, authCodeExpirationMillis);
	}

	/**
	 * 이메일 인증 코드 검증
	 *
	 * @param email
	 * @param authCode
	 */
	@Override
	public String verifySignUpEmailAuthCode(String email, String authCode) {
		String redisAuthCode = emailRedis.getData(email);

		if (redisAuthCode == null || redisAuthCode.isEmpty() || !redisAuthCode.equals(authCode)) {
			// 인증 실패

			log.debug(redisAuthCode + "          " + authCode);
			throw new EmailVerifiedException();
		}

		// 인증 성공 시 새로운 인증 코드를 생성해 응답
		// 추가정보를 포함해 회원가입 요청을 보낼 때 해당 값을 포함해야함
		authCode = createAuthCode();
		emailRedis.setData(email, authCode, authCodeExpirationMillis);

		return authCode;
	}

	/**
	 * 이메일 폼 생성
	 *
	 * @param toEmail
	 * @param title
	 * @param code
	 * @return
	 */
	private MimeMessage createAuthCodeEmailForm(String toEmail, String title, String code) {
		try {
			MimeMessage message = emailSender.createMimeMessage();

			message.setFrom(new InternetAddress(email, personal));
			message.setRecipients(Message.RecipientType.TO, toEmail);
			message.setSubject(title);
			String body = "<h3>요청하신 인증 번호입니다.</h3>" + "<h1>" + code + "</h1>" + "<h3>감사합니다.</h3>";
			message.setText(body, "UTF-8", "html");

			return message;
		} catch (MessagingException | UnsupportedEncodingException e) {
			throw new EmailSendingException("이메일 전송 실패");
		}
	}

	/**
	 * 인증 코드 생성
	 *
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	private String createAuthCode() {
		int length = authCodeLength;
		try {
			Random random = SecureRandom.getInstanceStrong();
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < length; i++) {
				builder.append(random.nextInt(10));
			}
			return builder.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new EmailSendingException("인증 메일 발송 실패");
		}
	}
}
