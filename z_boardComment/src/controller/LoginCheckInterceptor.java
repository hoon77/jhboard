package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


@Component
public class LoginCheckInterceptor extends
HandlerInterceptorAdapter{

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
        //eturn super.preHandle(request, response, handler);
		Object loginUser =  request.getSession().getAttribute("loginUser");
		
		if(loginUser== null) {
			
			response.sendRedirect("loginForm.do");
			return false;
		}
		
		return true;
	}

	
	
	
}
