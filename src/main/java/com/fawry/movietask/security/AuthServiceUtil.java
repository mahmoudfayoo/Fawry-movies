package com.fawry.movietask.security;

import feign.FeignException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthServiceUtil {
    private final String BEARER = "Bearer ";
    @Autowired
    private JwtService jwtService;

    private List<String> whiteListedActions = Arrays.asList("^/api/auth/login$", "^/api/auth/register$","^/api/h2-console/.*");

    private Map<String, List<String>> allowedActions = new HashMap<>();
    @Autowired
    private ServletResponse servletResponse;


    public AuthServiceUtil() {
        allowedActions.put("ADMIN", Arrays.asList("^/api/.*"));
        allowedActions.put("USER", Arrays.asList("^/api/user/.*"));
    }

    public String extractTokenFromRequest(ServletRequest request,ServletResponse servletResponse) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String bearerToke = httpServletRequest.getHeader("Authorization");
        validateAuthHeader(bearerToke,servletResponse);
        return getAccessToken(bearerToke);
    }

    private void validateAuthHeader(String bearerToke,ServletResponse servletResponse) {
        if (bearerToke == null || bearerToke.isBlank() || bearerToke.isEmpty()) {
            //throw new FeignException.Unauthorized("forbidden", null, null, null);
            perpaierForbiddenResponse((HttpServletResponse) servletResponse);
        }
    }

    private String getAccessToken(String bearerToke) {
        return bearerToke.replace(BEARER, "").trim();
    }

    public String extractUri(ServletRequest request) {
        HttpServletRequest httpServletRequet = (HttpServletRequest) request;
        String uri = httpServletRequet.getRequestURI();
        return uri;
    }

    public void isUserAllowed(String userType, String action, ServletResponse servletResponse) {
        List<String> clientAllowedAction = allowedActions.get(userType.toUpperCase());
        for (String allowedAction : clientAllowedAction) {
            if (action.matches(allowedAction)) {
                return;
            }
        }
        perpaierForbiddenResponse((HttpServletResponse) servletResponse);
    }

    public boolean isWhiteListedAction(String Uri) {
        for (String regex : whiteListedActions) {
            if (Uri.matches(regex)) {
                return true;
            }
        }
        return false;

    }

    @SneakyThrows
    private void perpaierForbiddenResponse(HttpServletResponse httpServletResponse) {
        httpServletResponse.setStatus(403);
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.getWriter().write("{\"message\": \"Invalid token\"}");
        httpServletResponse.getWriter().flush();
        throw new RuntimeException("Forbidden Request");
    }
}