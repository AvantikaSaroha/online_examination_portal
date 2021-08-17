package online_exam_portal.dao;

import online_exam_portal.model.User;
import online_exam_portal.model.Result;
import online_exam_portal.model.Test;
import online_exam_portal.model.Question;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
	
	private String jdbcurl="jdbc:mysql://localhost:3306/online_exam_portal";
	private String jdbcusername="root";
	private String jdbcpassword="";
	
	private static final String insert_user_sql= "insert into user_details"+ " (username,u_email,u_password) values "+"(?,?,PASSWORD(?));";
	private static final String insert_topic_sql= "insert into topic"+ " (topicname) values "+"(?);";
	private static final String insert_result_sql= "insert into result"+ " (userid,topicid,marksobt,totalmarks) values "+"(?,?,?,?);";
	private static final String insert_question_sql= "insert into question"+ " (que,opt1,opt2,opt3,opt4,ans,topicid) values "+"(?,?,?,?,?,?,?);";
	private static final String select_user_by_id= "select userid, username, u_email from user_details where userid=?";
	private static final String select_userid_by_email= "select userid from user_details where u_email=?";
	private static final String select_admin_by_id= "select adminid, adminame, a_email from admin_details where adminid=?";
	private static final String select_all_user="select userid, username, u_email from user_details";
	private static final String select_all_topics="select topicid, topicname from topic";
	private static final String select_topic="select topicname from topic where topicid=?";
	private static final String select_topic_id="select topicid from topic where topicname=?";
	private static final String select_all_questions="select que, opt1, opt2,opt3,opt4,ans from question where topicid=?";
	private static final String number_Of_Questions="select count(*) from question where topicid=?";
	private static final String select_all_result="select userid, totalmarks, marksobt from result where topicid=?";
	private static final String select_user_result="select result.totalmarks, result.marksobt,topic.topicname from result INNER JOIN topic ON result.topicid=topic.topicid where result.userid=?";
	private static final String select_topic_result="select result.totalmarks, result.marksobt,user_details.username from result INNER JOIN user_details ON result.userid=user_details.userid where result.topicid=?";
	private static final String delete_user_sql="delete from user_details where userid=?;";
	private static final String delete_test_sql="delete from topic where topicid=?;";
	private static final String update_user_sql="update user_details set username =?, u_email=?, u_password=?";
	private static final String update_user_password="update user_details set u_password=PASSWORD(?) where userid=?";
	private static final String update_Admin_password="update admin_details set a_password=PASSWORD(?) where adminid=?";
	private static final String update_user_password_email="update user_details set u_password=PASSWORD(?) where u_email=?";
	private static final String select_user_by_id_and_password= "select userid, username, u_email from user_details where userid=? and u_password=PASSWORD(?)";
	private static final String select_admin_by_id_and_password= "select adminid, adminame, a_email from admin_details where adminid=? and a_password=PASSWORD(?)";


	
	   public UserDAO() {}

	    protected Connection getConnection() throws SQLException {
	        Connection connection = null;
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            connection = DriverManager.getConnection(jdbcurl, jdbcusername, jdbcpassword);
	            }
	           
	        catch (ClassNotFoundException e) {
	            
	            e.printStackTrace();
	        }
	       
	        return connection;
	    }

	    public void insertUser(String username , String email , String password) throws SQLException {
	        // try-with-resource statement will auto close the connection.
	        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(insert_user_sql)) {
	            preparedStatement.setString(1, username);
	            preparedStatement.setString(2, email);
	            preparedStatement.setString(3, password);
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	    }
	    public void insertQuestion(String que, String opt1, String opt2, String opt3, String opt4, String ans,int topicid) throws SQLException {
	        // try-with-resource statement will auto close the connection.
	        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(insert_question_sql)) {
	            preparedStatement.setString(1, que);
	            preparedStatement.setString(2, opt1);
	            preparedStatement.setString(3, opt2);
	            preparedStatement.setString(4, opt3);
	            preparedStatement.setString(5, opt4);
	            preparedStatement.setString(6, ans);
	            preparedStatement.setInt(7, topicid);
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	    }
	    public void insertResult(int id,int topicid,int marksobt,int totalmarks) throws SQLException {
	        // try-with-resource statement will auto close the connection.
	        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(insert_result_sql)) {
	            preparedStatement.setInt(1, id);
	            preparedStatement.setInt(2, topicid);
	            preparedStatement.setInt(3, marksobt);
	            preparedStatement.setInt(4, totalmarks);
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	    }
	    public int insertTopic(String topicname) throws SQLException {
	        // try-with-resource statement will auto close the connection.
	        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(insert_topic_sql)) {
	            preparedStatement.setString(1, topicname);
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	        int topicid=0;
	        try (Connection connection = getConnection();
	        		
	            PreparedStatement preparedStatement = connection.prepareStatement(select_topic_id);) {
	            preparedStatement.setString(1, topicname);
	            ResultSet rs = preparedStatement.executeQuery();
	            while (rs.next()) {
	                topicid = rs.getInt("topicid");
	            }
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	        return topicid;
	    }
	    
	    public int numberOfQuestions(int topicid) {
	    	int number =0;
	        try (Connection connection = getConnection();
	            
	            PreparedStatement preparedStatement = connection.prepareStatement(number_Of_Questions);) {
	            preparedStatement.setInt(1, topicid);
	            ResultSet rs = preparedStatement.executeQuery();
	            while(rs.next()){
	                number = rs.getInt("count(*)");
	            }
	            
	          
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	        return number;
	    }
	    public String selectTopic(int topicid) {
	    	String topicname="";
	        try (Connection connection = getConnection();
	        		
	            PreparedStatement preparedStatement = connection.prepareStatement(select_topic);) {
	            preparedStatement.setInt(1, topicid);
	            ResultSet rs = preparedStatement.executeQuery();
	            while (rs.next()) {
	                topicname = rs.getString("topicname");
	            }
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	        return topicname;
	    }

	    public User selectUser(int id) {
	        User user = null;
	        
	        try (Connection connection = getConnection();
	        		
	            PreparedStatement preparedStatement = connection.prepareStatement(select_user_by_id);) {
	            preparedStatement.setInt(1, id);
	            ResultSet rs = preparedStatement.executeQuery();

	            while (rs.next()) {
	            	int userid = rs.getInt("userid");
	                String name = rs.getString("username");
	                String email = rs.getString("u_email");
	              
	                user = new User(userid, name, email);
	            }
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	        return user;
	    }
	    
	    
	    public int selectUserid(String email) {
	        int id =0;
	        try (Connection connection = getConnection();
	        		
	            PreparedStatement preparedStatement = connection.prepareStatement(select_userid_by_email);) {
	            preparedStatement.setString(1, email);
	            ResultSet rs = preparedStatement.executeQuery();
	            
	            while (rs.next()) {
	                id = rs.getInt("userid");

	            }
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	        return id;
	    }
	    
	    
	    public User selectAdmin(int id) {
	        User user = null;
	        
	        try (Connection connection = getConnection();
	            
	            PreparedStatement preparedStatement = connection.prepareStatement(select_admin_by_id);) {
	            preparedStatement.setInt(1, id);
	            ResultSet rs = preparedStatement.executeQuery();

	            
	            while (rs.next()) {
	            	int userid = rs.getInt("adminid");
	                String name = rs.getString("adminame");
	                String email = rs.getString("a_email");
	                
	                user = new User(userid, name, email);
	            }
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	        return user;
	    }
	    
	    public User selectUserPassword(int id, String password) {
	        User user = null;
	        
	        try (Connection connection = getConnection();
	            
	            PreparedStatement preparedStatement = connection.prepareStatement(select_user_by_id_and_password);) {
	            preparedStatement.setInt(1, id);
	            preparedStatement.setString(2, password);
	            ResultSet rs = preparedStatement.executeQuery();

	            
	            while (rs.next()) {
	            	int userid = rs.getInt("userid");
	                String name = rs.getString("username");
	                String email = rs.getString("u_email");
	                
	                user = new User(userid, name, email);
	            }
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	        return user;
	    }
	    public User selectAdminPassword(int id, String password) {
	        User user = null;
	        
	        try (Connection connection = getConnection();
	            
	            PreparedStatement preparedStatement = connection.prepareStatement(select_admin_by_id_and_password);) {
	            preparedStatement.setInt(1, id);
	            preparedStatement.setString(2, password);
	            ResultSet rs = preparedStatement.executeQuery();

	            
	            while (rs.next()) {
	            	int userid = rs.getInt("adminid");
	                String name = rs.getString("adminame");
	                String email = rs.getString("a_email");
	                
	                user = new User(userid, name, email);
	            }
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	        return user;
	    }

	    public List < User > selectAllUsers() {

	        List < User > users = new ArrayList < > ();
	        
	        try (Connection connection = getConnection();

	            PreparedStatement preparedStatement = connection.prepareStatement(select_all_user);) {
	            
	           
	            ResultSet rs = preparedStatement.executeQuery();
	            while (rs.next()) {
	                int id = rs.getInt("userid");
	                String name = rs.getString("username");
	                String email = rs.getString("u_email");
	                users.add(new User(id, name, email));
	            }
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	        return users;
	    }
	    
	    public List < Test > selectAllTests() {

	        List < Test > topics = new ArrayList < > ();
	        
	        try (Connection connection = getConnection();

	            PreparedStatement preparedStatement = connection.prepareStatement(select_all_topics);) {
	          
	           
	            ResultSet rs = preparedStatement.executeQuery();
	            while (rs.next()) {
	            	int topicid= rs.getInt("topicid");
	               String topic = rs.getString("topicname");
	                
	                topics.add(new Test(topicid, topic));
	                
	            }
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	        return topics;
	    }
	    
	    public List < Question > selectAllQuestions(int topicid) {

	        List < Question > questions = new ArrayList < > ();
	        
	        try (Connection connection = getConnection();

	            PreparedStatement preparedStatement = connection.prepareStatement(select_all_questions);) {
	        	preparedStatement.setInt(1, topicid);
	           
	            ResultSet rs = preparedStatement.executeQuery();
	            while (rs.next()) {
	            	String que= rs.getString("que");
	               String opt1 = rs.getString("opt1");
	               String opt2 = rs.getString("opt2");
	               String opt3 = rs.getString("opt3");
	               String opt4 = rs.getString("opt4");
	               String copt = rs.getString("ans");
	                questions.add(new Question(que, opt1,opt2,opt3,opt4,copt));
	                
	            }
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	        return questions;
	    }
	    
	    public List < Result > selectAllResult(int topic) {

	        List < Result > users = new ArrayList < > ();
	        
	        try (Connection connection = getConnection();

	            PreparedStatement preparedStatement = connection.prepareStatement(select_all_result);) {
	            preparedStatement.setInt(1, topic);

	           
	            ResultSet rs = preparedStatement.executeQuery();
	            while (rs.next()) {
	                int id = rs.getInt("userid");
	                try (
		            PreparedStatement preparedStatement1 = connection.prepareStatement(select_user_by_id);) {
		            preparedStatement1.setInt(1, id);
		            ResultSet rs1 = preparedStatement.executeQuery();
		            String username=null;
		            while (rs1.next()) {
		             username = rs1.getString("username");		                
		                }

	                int totalmarks = rs.getInt("totalmarks");
	                int marksobt= rs.getInt("marksobt");
	                users.add(new Result(username, totalmarks, marksobt));
	            }
	        }
	            } catch (SQLException e) {
	            printSQLException(e);
	        }
	        return users;
	    }
	    public List < Result > selectUserResult(int id) {

	        List < Result > users = new ArrayList < > ();
	        
	        try (Connection connection = getConnection();

	            PreparedStatement preparedStatement = connection.prepareStatement(select_user_result);) {
	            preparedStatement.setInt(1, id);
	            ResultSet rs = preparedStatement.executeQuery();
	            
	            while (rs.next()) {
	            	 String topicname = rs.getString("topic.topicname");	
		                int totalmarks = rs.getInt("result.totalmarks");
		                int marksobt= rs.getInt("result.marksobt");
		                users.add(new Result(totalmarks, marksobt,topicname));
		                System.out.println(topicname);
	        }
	            } catch (SQLException e) {
	            printSQLException(e);
	        }
	        return users;
	    }
	    public List < Result > selectTopicResult(int topicid) {

	        List < Result > users = new ArrayList < > ();
	        
	        try (Connection connection = getConnection();

	            PreparedStatement preparedStatement = connection.prepareStatement(select_topic_result);) {
	            preparedStatement.setInt(1, topicid);
	            ResultSet rs = preparedStatement.executeQuery();
	            
	            while (rs.next()) {
	            	 String topicname = rs.getString("user_details.username");	
		                int totalmarks = rs.getInt("result.totalmarks");
		                int marksobt= rs.getInt("result.marksobt");
		                users.add(new Result(totalmarks, marksobt,topicname));
	        }
	            } catch (SQLException e) {
	            printSQLException(e);
	        }
	        return users;
	    }

	    public boolean deleteUser(int id) throws SQLException {
	        boolean rowDeleted;
	        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(delete_user_sql);) {
	            statement.setInt(1, id);
	            
	            rowDeleted = statement.executeUpdate() > 0;
	        }
	        return rowDeleted;
	    }
	    public boolean deleteTest(int testid) throws SQLException {
	        boolean rowDeleted;
	        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(delete_test_sql);) {
	            statement.setInt(1, testid);
	            
	            rowDeleted = statement.executeUpdate() > 0;
	        }
	        return rowDeleted;
	    }

	    public boolean updateUser(User user) throws SQLException {
	        boolean rowUpdated;
	        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(update_user_sql);) {
	            statement.setInt(1, user.getUserid());
	            statement.setString(2, user.getUsername());
	            statement.setString(3, user.getU_email());

	            rowUpdated = statement.executeUpdate() > 0;
	        }
	        return rowUpdated;
	    }
	    public boolean updateUserPass(int id,String pass) throws SQLException {
	        boolean rowUpdated;
	        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(update_user_password);) {
	            statement.setString(1, pass);
	            statement.setInt(2, id);

	            rowUpdated = statement.executeUpdate() > 0;
	        }
	        return rowUpdated;
	    }
	    
	    public boolean updateAdminPass(int id,String pass) throws SQLException {
	        boolean rowUpdated;
	        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(update_Admin_password);) {
	            statement.setString(1, pass);
	            statement.setInt(2, id);

	            rowUpdated = statement.executeUpdate() > 0;
	        }
	        return rowUpdated;
	    }

	    public boolean updateUserPassEmail(String pass,String email) throws SQLException {
	        boolean rowUpdated;
	        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(update_user_password_email);) {
	            statement.setString(1, pass);
	            statement.setString(2, email);
	            System.out.println(email);
	            rowUpdated = statement.executeUpdate() > 0;
	        }
	        return rowUpdated;
	    }

	    
	    private void printSQLException(SQLException ex) {
	        for (Throwable e: ex) {
	            if (e instanceof SQLException) {
	                e.printStackTrace(System.err);
	                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
	                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
	                System.err.println("Message: " + e.getMessage());
	                Throwable t = ex.getCause();
	                while (t != null) {
	                    System.out.println("Cause: " + t);
	                    t = t.getCause();
	                }
	            }
	        }
	    }
	}
	
