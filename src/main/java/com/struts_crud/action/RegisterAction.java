package com.struts_crud.action;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;

import com.opensymphony.xwork2.ActionSupport;
import com.struts_crud.dao.UserDao;
import com.struts_crud.util.Validation;
public class RegisterAction extends ActionSupport {
    
	private static final long serialVersionUID = 1L;
	private String username;
    private String email;
    private String password;
    private String message;
    private List<String> roles;
    private String selectedRole;
    private UserDao userDao;
    
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(RegisterAction.class);

    public RegisterAction() {
    	roles = Arrays.asList("ADMIN", "USER");
    }
    
    public String registerUser() {
    	return SUCCESS;
    }

	public String register() {
    	Validation validation = new Validation();

        try {
        	logger.info("Register Starts");        	
             if(username.isEmpty() || password.isEmpty() || email.isEmpty() || selectedRole.isEmpty()) {
            	message = "Field cannot be empty";
            	return INPUT;
            }
            else if(!validation.isEmailValid(email)) {
            	message = "Enter the valid email format";
            	return INPUT;
            }
            else {
            	userDao = new UserDao();
                int result = userDao.registerUser(username, email, password, selectedRole);
                if (result > 0) {
                	return SUCCESS;
            } else {
                message = "Invalid credentials or username already exists.";
                return INPUT;
            }
            }
        } catch (Exception e) {
        	logger.error("Error on Register {}",e.getMessage());
        	message = "An error occured! Please try again";
            return INPUT;
        }
        finally {
        	logger.info("Register Ends");

        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getRoles() {
		return roles;
	}

	public String getSelectedRole() {
		return selectedRole;
	}

	public void setSelectedRole(String selectedRole) {
		this.selectedRole = selectedRole;
	}

}
