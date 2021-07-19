package interceptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthCheckInterceptor extends HandlerInterceptorAdapter {
    @Override //controller에 진입 전에 차단
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //session이 있는지 확인
        HttpSession session = request.getSession(false);
            if(session != null){
                Object authInfo = session.getAttribute("authInfo");
                if(authInfo != null){
                    return true;
                }
            }
            response.sendRedirect(request.getContextPath()+"/");
        return false;
    }

    @Override //Controller에 진입 후 view가 랜더링 전에 실행
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override //Contoller에 진입 후 view가 렌더링 한 후에 실행
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
