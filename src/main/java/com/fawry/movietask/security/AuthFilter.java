package com.fawry.movietask.security;

import jakarta.servlet.*;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class AuthFilter implements Filter {
    @Autowired
    private AuthServiceUtil authServiceUtil;
    @Autowired
    JwtService jwtService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        String userAction = authServiceUtil.extractUri(servletRequest);
        if(authServiceUtil.isWhiteListedAction(userAction)){
            filterChain.doFilter(servletRequest,servletResponse);
            return ;
        }
        try {
            String token = authServiceUtil.extractTokenFromRequest(servletRequest,servletResponse);
            jwtService.isTokenValid(token);
            String userType = jwtService.extractClaimsByKey("userType",token);
            authServiceUtil.isUserAllowed(userType,userAction,servletResponse);
        }catch (Exception e){
            return;
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
