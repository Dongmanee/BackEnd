package com.dongmanee.domain.email.service;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.dongmanee.domain.email.exception.EmailSendingException;
import com.dongmanee.domain.email.exception.EmailVerifiedException;
import com.dongmanee.domain.email.utils.EmailRedisUtils;
import com.dongmanee.domain.member.dao.MemberRepository;
import com.dongmanee.domain.member.exception.DuplicateEmailException;
import com.dongmanee.global.utils.AuthCodeProvider;

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
	private final MemberRepository memberJpaRepository;
	private final AuthCodeProvider authCodeProvider;

	@Value("${spring.mail.username}")
	private String email;

	@Value("${spring.mail.personal}")
	private String personal;

	@Value("${auth.code.expiration-millis}")
	private long authCodeExpirationMillis;

	/**
	 * 이메일 인증 코드 발송
	 *
	 * @param toEmail
	 */
	@Override
	public void sendSingUpEmailAuthCode(String toEmail) {
		if (memberJpaRepository.existsByEmail(toEmail)) {
			throw new DuplicateEmailException("이미 가입한 이메일입니다.");
		}

		String title = "동만이 이메일 인증";
		String authCode = authCodeProvider.createAuthCode();

		try {
			MimeMessage emailForm = createAuthCodeEmailForm(toEmail, title, authCode);
			emailSender.send(emailForm);
		} catch (MailSendException | MessagingException | UnsupportedEncodingException e) {
			// TODO 클라이언트 에러, 서버 에러 구분 -> 서버 에러시 로그 기록, 어떤 상태코드를 사용할지 고민
			throw new EmailSendingException();
		}

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
			throw new EmailVerifiedException();
		}

		// 인증 성공 시 새로운 인증 코드를 생성해 응답
		// 추가정보를 포함해 회원가입 요청을 보낼 때 해당 값을 포함해야함
		authCode = authCodeProvider.createAuthCode();
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
	private MimeMessage createAuthCodeEmailForm(String toEmail, String title, String code) throws
		UnsupportedEncodingException,
		MessagingException {
		MimeMessage message = emailSender.createMimeMessage();

		message.setFrom(new InternetAddress(email, personal));
		message.setRecipients(Message.RecipientType.TO, toEmail);
		message.setSubject(title);
		String body = "<h3>요청하신 인증 번호입니다.</h3>" + "<h1>" + code + "</h1>" + "<h3>감사합니다.</h3>";
		message.setText(body, "UTF-8", "html");

		return message;
	}
}
