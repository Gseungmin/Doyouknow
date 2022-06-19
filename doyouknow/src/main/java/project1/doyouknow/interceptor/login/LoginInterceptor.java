package project1.doyouknow.interceptor.login;

import org.springframework.web.servlet.HandlerInterceptor;
import project1.doyouknow.SessionConst;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER).equals(null)) {
            response.sendRedirect("/login");
            return false;
        } return true;
    }
}
