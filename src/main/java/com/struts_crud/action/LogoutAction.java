package com.struts_crud.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;

import java.util.Map;

public class LogoutAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(LogoutAction.class);


   
    public String logout() {
    	try {
    		logger.info("Logout starts");

    		Map<String, Object> session = ServletActionContext.getContext().getSession();
            
            session.clear();
            ServletActionContext.getRequest().getSession().invalidate();
         
            
            return SUCCESS; 
    	}
    	catch(Exception e) {
    		logger.error("Error on Logout {}",e.getMessage());
    		return ERROR;
    	}
    	finally {
    		logger.info("Logout ends");

    	}

        
    }
}