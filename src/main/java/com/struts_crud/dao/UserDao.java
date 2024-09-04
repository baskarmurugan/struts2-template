package com.struts_crud.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;

import com.struts_crud.model.User;
import com.struts_crud.util.ConfigUtil;

public class UserDao {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(UserDao.class);
    static String url = ConfigUtil.getProperty("database.url");
    static String user = ConfigUtil.getProperty("database.username");
    static String pass = ConfigUtil.getProperty("database.password");
	 

	public static Connection getConnection() throws Exception {
		try {
			Class.forName("org.postgresql.Driver");
			return DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			logger.error("Error on Get Connection {}",e.getMessage());
			return null;
		}
	}


	public int registerUser(String username, String email, String password, String role) throws Exception {
		int i = 0;
        Connection con = null;
        PreparedStatement ps = null;
		try {
			logger.info("Register User starts");
			String sql = "INSERT INTO strutscrud (username,email,password,role) VALUES (?,?,?,?)";
			con = getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, email);
			ps.setString(3, password);
			ps.setString(4, role);
			i = ps.executeUpdate();
			return i;
		} catch (Exception e) {
			logger.error("Error on Register User {}", e.getMessage());
			return i;
		} finally {
		        if (ps != null) ps.close();
		        if (con != null) con.close();
				logger.info("Register User Ends");

		}
	}


	public List<User> report() throws SQLException, Exception {
	    List<User> users = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
	    try {
			logger.info("Report User starts");
			con = getConnection();
	    	 ps = con.prepareStatement("SELECT id, username, email, password, role FROM strutscrud ORDER BY id");
	         rs = ps.executeQuery();
	         while (rs.next()) {
	            User user = new User(
	                rs.getString("username"),
	                rs.getString("email"),
	                rs.getString("password"),
	                rs.getString("role")
	            );
	            user.setId(rs.getLong("id"));
	            users.add(user);
	        }
	    }  catch (Exception e) {
			logger.error("Error on Report User {}",e.getMessage());
	        }
	    finally {
	    	 if (rs != null) rs.close();
	         if (ps != null) ps.close();
	         if (con != null) con.close();
			logger.info("Report User Ends");

	    }
	    return users;
	}

	 public User fetchUserById(Long id) throws SQLException, Exception {
	        User user = null;
	        Connection con = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        try {
				logger.info("Fetch User By Id starts");
	            con = getConnection();
	            ps = con.prepareStatement("SELECT id, username, email, password, role FROM strutscrud WHERE id = ?");
	            ps.setLong(1, id);
	            rs = ps.executeQuery();
	            if (rs.next()) {
	                user = new User(rs.getString("username"), rs.getString("email"), rs.getString("password"), rs.getString("role"));
	                user.setId(rs.getLong("id"));
	            }
	        } catch (Exception e) {
				logger.error("Error on Fetch User By Id {}",e.getMessage());
	        } finally {

	        	 if (rs != null) rs.close();
	             if (ps != null) ps.close();
	             if (con != null) con.close();
				logger.info("Fetch User By Id ends");

	        }
	        return user;
	    }
	
	
	public int updatePassword(Long id, String newPassword) throws Exception {
		Connection con = getConnection();
        PreparedStatement ps = null;
	    con.setAutoCommit(false);
	    int i = 0;
	    try{
			logger.info("Update Password starts");

		    String sql = "UPDATE strutscrud SET password = ? WHERE id = ?";
	        ps = getConnection().prepareStatement(sql); 
	        ps.setString(1, newPassword);
	        ps.setLong(2, id);
	        i = ps.executeUpdate();
	        con.commit();
	        return i;
	    }
	    catch (Exception e) {
			logger.error("Error on Update Password {}",e.getMessage());
	        con.rollback();
	        return 0;
	    } finally {
		        if (ps != null) ps.close();
		        if (con != null) con.close();
				logger.info("Update Password ends");

	    }
	}
	

	
	public User fetchUserDetails(String usernamehidden) throws SQLException, Exception {
		User user = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            logger.info("Fetch User Details starts");

            String sql = "SELECT id, username, email, password, role FROM strutscrud WHERE username=?";
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, usernamehidden);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                user = new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setId(rs.getLong("id"));
            }
        } catch (Exception e) {
            logger.error("Error on Fetch User Details {}", e.getMessage());
        } finally {

            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
            logger.info("Fetch User Details ends");

        }
        return user;
    }
	
	


	public int updateUserDetails(Long id, String username, String email, String password, String role) throws SQLException, Exception {
	    Connection con = getConnection();
        PreparedStatement ps = null;
	    con.setAutoCommit(false);
	    int i = 0;
	    try {
			logger.info("Update User Details starts");

	        String sql = "UPDATE strutscrud SET username=?, email=?, password=?, role=? WHERE id=?";
	        ps = con.prepareStatement(sql);
	        ps.setString(1, username);
	        ps.setString(2, email);
	        ps.setString(3, password);
	        ps.setString(4, role);
	        ps.setLong(5, id);
	        i = ps.executeUpdate();
	        con.commit();
	        return i;
	    } catch (Exception e) {
			logger.error("Error on Fetch User Details {}",e.getMessage());
	        con.rollback();
	        return 0;
	    } finally {
		        if (ps != null) ps.close();
		        if (con != null) con.close();
				logger.info("Update User Details ends");

	    }
	}



public int deleteUserDetails(Long id) throws SQLException, Exception {
    Connection con = getConnection();
    PreparedStatement ps = null;
    con.setAutoCommit(false);
    int i = 0;
    try {
		logger.info("Delete User Details starts");

    	System.out.println(id);
        String sql = "DELETE FROM strutscrud WHERE id=?";
        ps = con.prepareStatement(sql);
        ps.setLong(1, id);
        i = ps.executeUpdate();
        con.commit();
        return i;
    } catch (Exception e) {
		logger.error("Error on Delete User Details {}",e.getMessage());
        con.rollback();
        return 0;
    } finally {
	        if (ps != null) ps.close();
	        if (con != null) con.close();
			logger.info("Delete User Details ends");

    }
	
	}

public String getPasswordById(long id) throws Exception {
    String storedPassword = null;
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
		logger.info("Get Password By Id starts");

        con = getConnection();
        String sql = "SELECT password FROM strutscrud WHERE id = ?";
        ps = con.prepareStatement(sql);
        ps.setLong(1, id);
        rs = ps.executeQuery();

        if (rs.next()) {
            storedPassword = rs.getString("password");
        }
    } finally {

        if (rs != null) rs.close();
        if (ps != null) ps.close();
        if (con != null) con.close();
		logger.info("Get Password By Id ends");

    }

    return storedPassword;
}


}


