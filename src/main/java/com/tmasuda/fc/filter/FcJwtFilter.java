package com.tmasuda.fc.filter;

import com.auth0.jwt.JWTVerifier;
import com.tmasuda.fc.prop.JsonWebTokenProperties;
import org.jboss.logging.Logger;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class FcJwtFilter extends GenericFilterBean {

    private static final String OAUTH_START_WITH = "Bearer ";
    private static final String WAKE_UP_URL = "/status/wake-up";
    private static final String FAVICON_URL = "/favicon.ico";
    private static final Logger LOGGER = Logger.getLogger(FcJwtFilter.class);

    private JsonWebTokenProperties jsonWebTokenProperties;

    public FcJwtFilter(JsonWebTokenProperties jsonWebTokenProperties) {
        this.jsonWebTokenProperties = jsonWebTokenProperties;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws ServletException, IOException {

        final HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        final String authHeader = httpRequest.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith(OAUTH_START_WITH)) {
            handleInvalidAccess(servletRequest, servletResponse, "Empty or wrong format in Authorization header");
        } else {
            final String token = authHeader.split(OAUTH_START_WITH)[1];
            final JWTVerifier jwtVerifier = new JWTVerifier(jsonWebTokenProperties.getSecretKey(), jsonWebTokenProperties.getAudience(), jsonWebTokenProperties.getIssuer());

            try {
                Map<String, Object> claims = jwtVerifier.verify(token);
                final String snsId = (String) claims.get("sub");
                httpRequest.setAttribute("SNS_ID", snsId);
                chain.doFilter(httpRequest, servletResponse);
            } catch (Exception e) {
                handleInvalidAccess(servletRequest, servletResponse, e.getMessage());
            }
        }

    }

    private void handleInvalidAccess(ServletRequest servletRequest, ServletResponse servletResponse, String message) throws IOException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        if (WAKE_UP_URL.equals(req.getRequestURI()) || FAVICON_URL.equals(req.getRequestURI())) {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.setStatus(HttpServletResponse.SC_OK);
            LOGGER.info("Wake-up request has been received.");
        } else {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            LOGGER.error(message);
        }
    }

}
