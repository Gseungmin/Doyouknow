package project1.doyouknow.interceptor.content;

import org.springframework.web.servlet.HandlerInterceptor;
import project1.doyouknow.SessionConst;
import project1.doyouknow.domain.member.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ContentVideoInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if (session != null) {
            Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
            response.sendRedirect("/board/Video/login?loginId=" + member.getLoginId());
            return false;
        } return true;
    }
}
