package com.example.service.emailService;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.mail.internet.MimeUtility;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lilemin on 16/8/22.
 */
@Service
public class EmailSender extends EmailClient {

    private static Map<String, Object> proMap;

    private static VelocityEngine velocityEngine = null;

    static {
        proMap = new HashMap<String, Object>();
        proMap.put("resource.loader", "class");
        proMap.put("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

    }

    public String sendSimpleMail(Map<String,Object> model, String vmfile, String subject, String[] toAddrs) {
        try {
            SimpleEmail email = new SimpleEmail();
            email.setSocketTimeout(TIME_OUT);
            email.setAuthentication(userName, password);
            email.setHostName(emailServerAddr);
            email.setSSL(true);//走SSL 465端口，默认的25端口虽然可以本地发，但是在服务器上一般是被封的
            email.setSslSmtpPort("465");
            if (toAddrs != null && toAddrs.length > 0) {
                for (String toAddr : toAddrs) {
                    email.addTo(toAddr);
                }
            } else {
                return null;
            }
            email.setFrom(fromAddr);
            if (StringUtils.isBlank(subject)) {
                subject = defaultSubject;
            }
            try {
                email.setSubject(MimeUtility.encodeText(subject,MimeUtility.mimeCharset("gb2312"), null));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            String text = VelocityEngineUtils.mergeTemplateIntoString(
                    EmailSender.getVelocityEngineInstance(), vmfile, "UTF-8", model);
            email.setContent(text, contentType);
            return email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static VelocityEngine getVelocityEngineInstance() {
        if (null == velocityEngine) {
            synchronized (VelocityEngine.class) {
                if (null == velocityEngine) {
                    velocityEngine = new VelocityEngine();
                    for (Map.Entry<String, Object> entry : proMap.entrySet()) {
                        velocityEngine.setProperty(entry.getKey(), entry.getValue());
                    }
                }
            }
        }
        return velocityEngine;
    }
}
