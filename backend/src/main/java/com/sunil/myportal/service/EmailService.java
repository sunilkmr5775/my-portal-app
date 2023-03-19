package com.sunil.myportal.service;

import com.sunil.myportal.constant.ConstantVariables;
import com.sunil.myportal.constant.ExceptionConstant;
import com.sunil.myportal.constant.StatusConstant;
import com.sunil.myportal.dto.BaseResponse;
import com.sunil.myportal.model.ApplicationProperties;
import com.sunil.myportal.repository.ApplicationPropertiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.List;
import java.util.Properties;

@Service
public class EmailService {

    @Autowired
    private ApplicationPropertiesRepository applicationPropertiesRepository;

    public EmailService(ApplicationPropertiesRepository applicationPropertiesRepository) {
        this.applicationPropertiesRepository = applicationPropertiesRepository;
    }

    public BaseResponse sendEmailNotification(String CONTENT, String SUBJECT, String RECEIVER_EMAIL) {
        String TEMP_SENDER_EMAIL = null;
        String TEMP_PASSWORD = null;
        BaseResponse response = new BaseResponse();
        List<ApplicationProperties> applicationPropertiesList = applicationPropertiesRepository
                .findApplicationPropertiesListByAttributeTypeAndStatus(ConstantVariables.SMTP_ATTRIBUTES,
                        StatusConstant.STATUS_ACTIVE);

//      Setup host and mail server
        Properties properties = new Properties();
        try {
            for (ApplicationProperties ruleAttribute : applicationPropertiesList) {
                if (ruleAttribute.getRuleKey().equalsIgnoreCase("spring.mail.properties.mail.smtp.auth")) {
                    properties.put("mail.smtp.auth", Boolean.parseBoolean(ruleAttribute.getRuleValue()));
                }

                if (ruleAttribute.getRuleKey().equalsIgnoreCase("spring.mail.properties.mail.smtp.starttls.enable")) {
                    properties.put("mail.smtp.starttls.enable", Boolean.parseBoolean(ruleAttribute.getRuleValue()));
                }

                if (ruleAttribute.getRuleKey().equalsIgnoreCase("spring.mail.host")) {
                    properties.put("mail.smtp.host", ruleAttribute.getRuleValue());
                }

                if (ruleAttribute.getRuleKey().equalsIgnoreCase("spring.mail.port")) {
                    properties.put("mail.smtp.port", Integer.parseInt(ruleAttribute.getRuleValue()));
                }
                if (ruleAttribute.getRuleKey().equalsIgnoreCase("spring.mail.username")) {
                    TEMP_SENDER_EMAIL = ruleAttribute.getRuleValue();
                }
                if (ruleAttribute.getRuleKey().equalsIgnoreCase("spring.mail.password")) {
                    TEMP_PASSWORD = ruleAttribute.getRuleValue();
                }
               /* if (ruleAttribute.getRuleKey().equalsIgnoreCase("spring.mail.subject")) {
                    SUBJECT = ruleAttribute.getRuleValue();
                }*/
            }

            final String SENDER_EMAIL = TEMP_SENDER_EMAIL;
            final String PASSWORD = TEMP_PASSWORD;

            // get the session object and pass username and password
            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication(){
                    return new PasswordAuthentication(SENDER_EMAIL, PASSWORD);
                }
            });

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(CONTENT, "text/html");
            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(TEMP_SENDER_EMAIL));
            message.setContent(multipart);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(RECEIVER_EMAIL));
            message.setSubject(SUBJECT);
            message.setText(CONTENT, "UTF-8", "html");
            Transport.send(message);

            response.setStatus(ExceptionConstant.SUCCESS_ED);
            response.setErrorCode(ExceptionConstant.SUCCESS_EC);
            response.setErrorDescription(ExceptionConstant.EMAIL_SENT_ED);

        } catch (Exception e) {
            System.out.println("EmailService File Error: " + e.getMessage());
            response.setStatus(ExceptionConstant.FAILURE_ED);
            response.setErrorCode(ExceptionConstant.EMAIL_SENT_EC);
            response.setErrorDescription(e.getMessage());
        }
        return response;
    }
}
