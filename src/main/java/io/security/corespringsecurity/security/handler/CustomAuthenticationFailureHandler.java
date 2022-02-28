package io.security.corespringsecurity.security.handler;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private RequestCache requestCache = new HttpSessionRequestCache(); // 사용자가 요청한 객체를 참조

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    /**
     *
     * @param request
     * @param response
     * @param exception
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        String errorMassage = "Invalid Username or Password";

        SavedRequest savedRequest = requestCache.getRequest(request, response); // 요청한 정보

        if(exception instanceof BadCredentialsException) {
            errorMassage = "Invalid Username or Password";
        } else if(exception instanceof InsufficientAuthenticationException){
            errorMassage = "Invalid Secret Key";
        }

        setDefaultFailureUrl("/login?error=true&exception" + exception.getMessage());

        super.onAuthenticationFailure(request, response, exception);
    }
}