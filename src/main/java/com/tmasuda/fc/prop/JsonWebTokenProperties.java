package com.tmasuda.fc.prop;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JsonWebTokenProperties {

    @Value("${com.tmasuda.fc.jwt.audience}")
    private String audience;

    @Value("${com.tmasuda.fc.jwt.secret_key}")
    private String secretKey;

    @Value("${com.tmasuda.fc.jwt.issuer}")
    private String issuer;

    public String getAudience() {
        return audience;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public String getIssuer() {
        return issuer;
    }

}
