package com.tmasuda.fc.filter;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;
import com.tmasuda.fc.prop.JsonWebTokenProperties;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Map;

public class FcJwtFilter extends GenericFilterBean {

    private static final String OAUTH_START_WITH = "Bearer ";

    private JsonWebTokenProperties jsonWebTokenProperties;

    public FcJwtFilter(JsonWebTokenProperties jsonWebTokenProperties) {
        this.jsonWebTokenProperties = jsonWebTokenProperties;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletReponse, FilterChain chain) throws ServletException, IOException {

        final HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        final String authHeader = httpRequest.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith(OAUTH_START_WITH)) {
            throw new ServletException("Missing or invalid Authorization header.");
        }

        final String token = authHeader.split(OAUTH_START_WITH)[1];
        final JWTVerifier jwtVerifier = new JWTVerifier(jsonWebTokenProperties.getSecretKey(), jsonWebTokenProperties.getAudience(), jsonWebTokenProperties.getIssuer());

        try {
            Map<String, Object> claims = jwtVerifier.verify(token);
            final String snsId = (String) claims.get("sub");
            httpRequest.setAttribute("SNS_ID", snsId);
        } catch (InvalidKeyException e) {
            throw new IOException("Invalid JWT.");
        } catch (NoSuchAlgorithmException e) {
            throw new IOException("Invalid JWT.");
        } catch (IllegalStateException e) {
            throw new IOException("Invalid JWT.");
        } catch (SignatureException e) {
            throw new IOException("Invalid JWT.");
        } catch (JWTVerifyException e) {
            throw new IOException("Invalid JWT.");
        }

        chain.doFilter(httpRequest, servletReponse);
    }

}
