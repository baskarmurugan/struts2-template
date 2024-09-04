package com.struts_crud.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;

import com.opensymphony.xwork2.ActionSupport;
import com.struts_crud.dao.UserDao;
import com.struts_crud.model.User;
import com.struts_crud.util.Validation;

public class UserAction extends ActionSupport {
   
	private static final long serialVersionUID = 1L;
	private Long id;
	private String username;
	private String email;
    private String token;
	private String password;
	private String currentPassword;
	private String newPassword;
	private String confirmPassword;
	private String role;
    private String message;
	private String usernamehidden;
	private User user;
	private List<User> users;
	private List<String> roles = new ArrayList<String>(List.of("ADMIN", "USER"));
	
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(UserAction.class);
    private static final UserDao dao = new UserDao();
    private static final Validation validation = new Validation();
    
    Map<String, Object> session = ServletActionContext.getContext().getSession();
    
    public String viewUser() {
        try {
        	System.out.println("Viewwww");
            logger.info("View User Starts");
            id = (Long) session.get("id");
            user = dao.fetchUserById(id);
            if (user != null) {
                return SUCCESS;
            } else {
                message = "No user found with the given ID.";
                return ERROR;
            }
        } catch (Exception e) {
            logger.error("Error on View User {}", e.getMessage());
            return ERROR;
        } finally {
            logger.info("View User Ends");
        }
    }
    
    
    public String changePassword() {
	   return SUCCESS;
	}

   public String updatePassword() {

	    try {
	    	if(null == currentPassword && null == newPassword) {
	    		
    			return "userdashboard";
    		}
	    	logger.info("Update Password Starts");
			id = (Long) session.get("id");
	    	String storedPassword = dao.getPasswordById(id);
	    	if(currentPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
	    		message = "Field cannot be empty";
	    		return INPUT;
	    	}
	    	else {
	        if (!storedPassword.equals(currentPassword)) {
	            message = "Current password is incorrect.";
	            return INPUT;
	        }
	    	 if (!validation.doPasswordsMatch(newPassword, confirmPassword)) {
	    	        message = "New Password and Confirm Password do not match.";
	    	        return INPUT;
	    	    }
	    	 if (newPassword.equals(currentPassword)) {
	             message = "New password cannot be the same as the current password.";
	             return INPUT;
	         }
	    

	        int result = dao.updatePassword(id, newPassword);
	        if (result > 0) {
	            message = "Password updated successfully.";
	            return SUCCESS;
	        } else {
	            message = "Failed to update the password.";
	            return ERROR;
	        }
	        
	    }
	    }catch (Exception e) {
	    	logger.error("Error on Update Password {}",e.getMessage());
	    	message = "An error occurred. Please try again.";
	        return ERROR;
	    }
	    finally{
	    	logger.info("Update Password Ends");

	    }
	}

    public String listUser() throws Exception {
    	try {
            logger.info("List Users Starts");
            users = (List<User>) dao.report();
            return SUCCESS;
        } catch (Exception e) {
            logger.error("Error on List Users {}", e.getMessage());
            return ERROR;
        } finally {
            logger.info("List Users Ends");
        }
    }
    

    public String createUser() {
        try {
            logger.info("Create User Starts");
            int result = dao.registerUser(username, email, password, role);
            if (result > 0) {
                message = "User created successfully.";
                return SUCCESS;
            } else {
                message = "Failed to create user.";
                return INPUT;
            }
        } catch (Exception e) {
            logger.error("Error on create user: {}", e.getMessage());
            message = "An error occurred while creating the user.";
            return ERROR;
        } finally {
            logger.info("Create User Ends");
        }
    }

    public String editUser() throws Exception {
   
        try {
        	logger.info("Edit User Starts");
        	if(null == id) {
    			return "admindashboard";
    		}
            user = dao.fetchUserById(id);
            setUsername(user.getUsername());
            setEmail(user.getEmail());
            setPassword(user.getPassword());
            setRole(user.getRole());
            roles.remove(role);
            return SUCCESS;
        } catch (Exception e) {
        	logger.error("Error on Edit User {}", e.getMessage());
        	return ERROR;
        } finally {
          
                logger.info("Edit User Ends");
            }
        
    }
    
    public String updateUser() throws Exception {
    	
        try {
        	logger.info("Update User Starts");
        	if(null == username && null == email && null == password && null == role) {
	    		
    			return "admindashboard";
    		}
        	if(validation.isEmailValid(email)) {
        		
            int result = dao.updateUserDetails(id, username, email, password, role);
            if (result > 0) {
            	message = "Updated successfully";
                return SUCCESS;
            } else {
            	message = "Username or Email already exists";
                return INPUT;
            }
        	}
        	else {
        		message = "Enter the valid email format";
        		return INPUT;
        	}
        } catch (Exception e) {
        	logger.error("Error on Update User {}",e.getMessage());
        	message = "Please try again";
            return INPUT;
        }
        finally {
            logger.info("Update User Ends");
        }
}
    
    
    public String deleteUser() throws Exception {
    
        try {
        	logger.info("Delete User Starts");
        	if(null == id) return "admindashboard";
        int result = dao.deleteUserDetails(id);
        if(result > 0) {
        	message = "Deleted successfully";
        	return SUCCESS;
        }
        else {
        	message = "Please try again";
        	return ERROR;
        }
        }
        catch(Exception e) {
        	logger.error("Error on Delete User {}",e.getMessage());
            message = "An error occurred while deleting the user.";
        	return ERROR;
        	
        }
        finally {
        	logger.info("Delete User Ends");
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

	public String getUsernamehidden() {
		return usernamehidden;
	}

	public void setUsernamehidden(String usernamehidden) {
		this.usernamehidden = usernamehidden;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
    public Long getId() {
		return id;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public void setId(Long id) {
		this.id = id;
	}

    public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	  public String getToken() {
			return token;
		}
	    public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public String getConfirmPassword() {
			return confirmPassword;
		}

		public void setConfirmPassword(String confirmPassword) {
			this.confirmPassword = confirmPassword;
		}
		public String getCurrentPassword() {
			return currentPassword;
		}

		public void setCurrentPassword(String currentPassword) {
			this.currentPassword = currentPassword;
		}

		public List<String> getRoles() {
			return roles;
		}

		public void setRoles(List<String> roles) {
			this.roles = roles;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		

	

}
