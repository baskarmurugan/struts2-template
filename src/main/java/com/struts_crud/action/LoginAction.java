package com.struts_crud.action;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;

import com.opensymphony.xwork2.ActionSupport;
import com.struts_crud.dao.UserDao;
import com.struts_crud.model.User;
import com.struts_crud.util.JwtUtil;

public class LoginAction extends ActionSupport {
   
	private static final long serialVersionUID = 1L;
	private String username;
    private String password;
    private String message;
    private UserDao userDao;
    private String token;
    private List<String> roles;
    private List<User> users;
    
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(LoginAction.class);
    
    Map<String, Object> session = ServletActionContext.getContext().getSession();


    public LoginAction() {
    	roles = Arrays.asList("ADMIN", "USER");
    	userDao = new UserDao();

    }

   
    public String login() {
    	try {
    		
            logger.info("Login Starts");
            if(null == username && null == password) {
	    		
    			return "login";
    		}
          if (username.isEmpty() || password.isEmpty()) {
                message = "Field cannot be empty";
                return INPUT;
            }

            User user = userDao.fetchUserDetails(username);
            
            if (user != null && user.getPassword().equals(password)) {
                token = JwtUtil.generateToken(user);
                session.put("id", user.getId());
                session.put("user", user.getUsername());
                session.put("role", user.getRole());
                session.put("token", token);
                
                ServletActionContext.getRequest().setAttribute("token", token);


                if (user.getRole().equalsIgnoreCase("ADMIN")) {
                    return SUCCESS;
                }
                return "usersuccess";
            } else {
                message = "Invalid username or password!";
                return INPUT;
            }
        } catch (Exception e) {
            logger.error("Error on Login {}", e.getMessage());
            message = "An error occurred while logging in.";
            return ERROR;
        } finally {
            logger.info("Login Ends");
        }
    }
    

 
	public String getToken() {
		return token;
	}

    public String getPassword() {
        return password;
    }

    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	public List<String> getRoles() {
		return roles;
	}

}
