package com.example.service.emailService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by lilemin on 16/8/22.
 */
@Service
public class EmailClient {
    protected final int TIME_OUT = 10000;

    @Value("${email.smtp}")
    protected String emailServerAddr;

    @Value("${email.fromAddr}")
    protected String fromAddr;

    @Value("${email.username}")
    protected String userName;

    @Value("${email.password}")
    protected String password;


    protected String contentType = "text/html;charset=UTF-8";

    protected String defaultSubject = "unknown subject";

}
