package com.struts_crud.interceptor;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.struts_crud.util.JwtUtil;

public class JwtInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;
	List<String> userPath = List.of("viewUser", "updatePassword", "logout", "changePassword");
	
	@Override
    public String intercept(ActionInvocation invocation) throws Exception {
		
		HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpSession session1 = request.getSession(false);
        
        if (session1 == null || session1.getAttribute("user") == null) {
            if (request.getHeader("Referer") == null) {
                return "login"; 
            }
        	else {
                return "sessionTimeout";
            }
        }
        
        String requestURL = request.getRequestURI();
        String authHeader = request.getHeader("Authorization");
        Map<String, Object> session = ServletActionContext.getContext().getSession();
        String token = null;
        String role = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
        }
        else {
            token = (String) session.get("token");
            role = JwtUtil.extractRole(token);
        }

        if (token != null && JwtUtil.isTokenValid(token) && customRoleAuth(role, requestURL)) {

            String username = JwtUtil.extractUsername(token);
            request.setAttribute("username", username);
            return invocation.invoke();
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return "unauthorized";
        }
    }
	private boolean customRoleAuth(String role, String path) {
		
		if ("USER".equalsIgnoreCase(role)) {
            for (String user : userPath) {
                if (path.contains(user)) {
                    return true;
                }
            }
        } else if ("ADMIN".equalsIgnoreCase(role)) {
                 return true;
        }

        return false;
    }
    }

