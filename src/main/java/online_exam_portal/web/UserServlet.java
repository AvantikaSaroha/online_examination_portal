package online_exam_portal.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import online_exam_portal.model.User;
import online_exam_portal.model.Result;
import online_exam_portal.model.Test;
import online_exam_portal.model.Question;
import online_exam_portal.dao.UserDAO;
import online_exam_portal.JavaEmail;

@WebServlet("/")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1;
    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String action = request.getServletPath();
        
        try {
            switch (action) {
            case "/":
                showLogin(request, response);
                break;
            case "/adminHome":
                showAdminHome(request, response);
                break;
                case "/adminProfile":
                    showAdminProfile(request, response);
                    break;
                case "/adminResult":
                    showAdminResult(request, response);
                    break;
                case "/manageTests":
                    showManageTests(request, response);
                    break;
                case "/manageUser":
                	showManageUser(request, response);
                    break;
                case "/userDashboard":
                    showUserDashboard(request, response);
                    break;
                case "/resultPage":
                    showResultPage(request, response);
                    break;  
                case "/profilePage":
                    showProfilePage(request, response);
                    break;
                case "/examPage":
                    showExamPage(request, response);
                    break;
                case "/forgotPassword":
                    showForgotPassword(request, response);
                    break;
                case "/setPassword":
                    showSetPassword(request, response);
                    break;
                case "/DoneSetPassword":
                    showDoneSetPassword(request, response);
                    break;
                case "/createTest":
                    showCreateTest(request, response);
                    break;
                case "/authentication":
                    authentication(request, response);
                    break;    
                default:
                    listUser(request, response);
                    break;
            }
        }catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        catch (SQLException ex) {
            throw new ServletException(ex);
        } 
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        List < User > listUser = userDAO.selectAllUsers();
        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showAdminHome(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
    	if(request.getParameter("id")==null) {
    		RequestDispatcher dispatcher = request.getRequestDispatcher("loginPage.jsp");
            dispatcher.include(request, response);
    	}
    	else {
    	int id = Integer.parseInt(request.getParameter("id"));
    	User existingUser = userDAO.selectAdmin(id);
        request.setAttribute("user", existingUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("adminHome.jsp");
        dispatcher.include(request, response);
    	}
    }
    
    private void showLogin(HttpServletRequest request, HttpServletResponse response)
    	    throws ServletException, IOException {
      
    	        RequestDispatcher dispatcher = request.getRequestDispatcher("loginPage.jsp");
    	        dispatcher.include(request, response);
    	    }
    
    private void showForgotPassword(HttpServletRequest request, HttpServletResponse response)
    	    throws ServletException, IOException,MessagingException {
    	String numbers = "0123456789";
    	  
        // Using random method
        Random rndm_method = new Random();
  
        char[] otp = new char[4];
  
        for (int i = 0; i < 4; i++)
        {
            // Use of charAt() method : to get character value
            // Use of nextInt() as it is scanning the value as int
            otp[i] =
             numbers.charAt(rndm_method.nextInt(numbers.length()));
        }
    	if(request.getParameter("email")!=null) {
    		String sotp = String.valueOf(otp);
        	String email = request.getParameter("email");
        	System.out.println("a");
        	try{
        		JavaEmail em = new JavaEmail();
        	em.send(sotp,email);

	        request.setAttribute("sotp", sotp);
	        request.setAttribute("email", email);

	        RequestDispatcher dispatcher = request.getRequestDispatcher("otpVerify.jsp");
	        dispatcher.include(request, response);
        	}
        	catch(Exception e) {
        		System.out.println(e);  

    	        RequestDispatcher dispatcher = request.getRequestDispatcher("forgotPassword.jsp");
    	        dispatcher.include(request, response);
        	}
        	
    	}
    	else {
    		System.out.println("b");
    		 RequestDispatcher dispatcher = request.getRequestDispatcher("forgotPassword.jsp");
 	        dispatcher.include(request, response);
    	}
        
    	    }
    
    private void showSetPassword(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, ServletException, IOException {
    	    	if(request.getParameter("uotp")==null||request.getParameter("sotp")==null) {
    	    		RequestDispatcher dispatcher = request.getRequestDispatcher("loginPage.jsp");
    	            dispatcher.include(request, response);
    	    	}
    	    	else {
    	    	String uotp = request.getParameter("uotp");
    	    	String sotp = request.getParameter("sotp");
    	    	String email = request.getParameter("email");
    	    	
    	    	if(uotp.equals(sotp)) {
    	    		request.setAttribute("email", email);
    	    		RequestDispatcher dispatcher = request.getRequestDispatcher("setPassword.jsp");
        	        dispatcher.include(request, response);
    	    	}
    	    	else {
    	    		RequestDispatcher dispatcher = request.getRequestDispatcher("forgotPassword.jsp");
    	        dispatcher.include(request, response);
    	        }
    	    }
    }
    private void showDoneSetPassword(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, ServletException, IOException {
    	    	if(request.getParameter("cpass")==null||request.getParameter("npass")==null) {
    	    		RequestDispatcher dispatcher = request.getRequestDispatcher("loginPage.jsp");
    	            dispatcher.include(request, response);
    	    	}
    	    	else {
    	    	String npass = request.getParameter("npass");
    	    	String cpass = request.getParameter("cpass");
    	    	String email = request.getParameter("email");
    	    	if(cpass.equals(npass)) {
    	    		userDAO.updateUserPassEmail(npass,email);
    	    		RequestDispatcher dispatcher = request.getRequestDispatcher("loginPage.jsp");
        	        dispatcher.include(request, response);
    	    	}
    	    	else {RequestDispatcher dispatcher = request.getRequestDispatcher("forgotPassword.jsp");
    	        dispatcher.include(request, response);
    	        }
    	    }
    }
    
    private void showAdminProfile(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, ServletException, IOException {
    	    	if(request.getParameter("id")==null) {
    	    		RequestDispatcher dispatcher = request.getRequestDispatcher("loginPage.jsp");
    	            dispatcher.include(request, response);
    	    	}
    	    	else {
    	    	int id = Integer.parseInt(request.getParameter("id"));
    	    	User existingUser = userDAO.selectAdmin(id);
    	        request.setAttribute("user", existingUser);
    	        
    	        
    	        String opass = request.getParameter("opass");
    	        String npass = request.getParameter("npass");
    	        String cpass = request.getParameter("cpass");
    	        
    	        if(opass!=null&&npass!=null&&cpass!=null) {
    	        	if(npass.equals(cpass)) {
    	        		User existingUser1 = userDAO.selectAdminPassword(id, opass);
        	    		if(existingUser1!=null){
        	    			userDAO.updateAdminPass(id, npass);
        	    			request.setAttribute("success", "Password Changed Successfully");
        	    		}
        	    		else {
        	    			request.setAttribute("error", "Wrong Password");
        	    		}
    	        	}
    	        	else {System.out.println(cpass+" "+npass);
    	        		request.setAttribute("error", "Password does not match");
    	        	}
    	        }
    	        
    	        
    	        RequestDispatcher dispatcher = request.getRequestDispatcher("adminProfile.jsp");
    	        dispatcher.include(request, response);
    	    }
    }
    
    private void showAdminResult(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, ServletException, IOException {
    	    	if(request.getParameter("id")==null) {
    	    		RequestDispatcher dispatcher = request.getRequestDispatcher("loginPage.jsp");
    	            dispatcher.include(request, response);
    	    	}
    	    	else {
    	    	int id = Integer.parseInt(request.getParameter("id"));
    	    	User existingUser = userDAO.selectAdmin(id);
    	        request.setAttribute("user", existingUser);
    	        List < Test > listTest = userDAO.selectAllTests();
    	        request.setAttribute("listTest", listTest);
    		if(request.getParameter("topicid")!=null) {
    			int topic= Integer.parseInt(request.getParameter("topicid"));
    		request.setAttribute("topic", topic);
    		List < Result > listResult = userDAO.selectTopicResult(topic);
            request.setAttribute("listResult", listResult);
            }
    	        RequestDispatcher dispatcher = request.getRequestDispatcher("adminResult.jsp");
    	        dispatcher.include(request, response);
    	    }
    }
    private void showManageTests(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, ServletException, IOException {
    	    	if(request.getParameter("id")==null) {
    	    		RequestDispatcher dispatcher = request.getRequestDispatcher("loginPage.jsp");
    	            dispatcher.include(request, response);
    	    	}
    	    	else {
    	    	int id = Integer.parseInt(request.getParameter("id"));
    	    	if(request.getParameter("delid")!=null) {
    	    		int delid = Integer.parseInt(request.getParameter("delid"));
		    		userDAO.deleteTest(delid);
    		    	request.setAttribute("status", "Topic Deleted Succesfully");
		    	}
    	    	
    	    	User existingUser = userDAO.selectAdmin(id);
    	        request.setAttribute("user", existingUser);
    	 List < Test > listTest = userDAO.selectAllTests();
         request.setAttribute("listTest", listTest);
    	        RequestDispatcher dispatcher = request.getRequestDispatcher("manageTests.jsp");
    	        dispatcher.include(request, response);
    	    }
    }
    
    private void showManageUser(HttpServletRequest request, HttpServletResponse response)
    		 throws SQLException, ServletException, IOException {
    		    	if(request.getParameter("id")==null) {
    		    		RequestDispatcher dispatcher = request.getRequestDispatcher("loginPage.jsp");
    		            dispatcher.include(request, response);
    		    	}
    		    	else {
    		    	int id = Integer.parseInt(request.getParameter("id"));
    		    	if(request.getParameter("delid")!=null) {

        		    	int delid = Integer.parseInt(request.getParameter("delid"));
        		    	
        		    	userDAO.deleteUser(delid);
        		    	request.setAttribute("status", "User Deleted Succesfully");
    		    	}
    		    	if(request.getParameter("username")!=null && request.getParameter("email")!=null &&request.getParameter("password")!=null) {
    		    		 String username = request.getParameter("username");
    		    	    	String email = request.getParameter("email");
    		    	        String password = request.getParameter("password");
    		    	        userDAO.insertUser(username,email,password);
    		    	        int nid = userDAO.selectUserid(email);
    		    	        JavaEmail jm = new JavaEmail();
    		    	        String subject = "Welcome to Online Exam portal";
    		    	        jm.send(email,subject,"Hiii "+username+",<br> You have been succesfully registered on Online Examination Portal<br> Your User id is - "+nid+"<br> Your Password is "+password);
    		    	        
        	    	}
    		    	System.out.println(id);
    		    	User existingUser = userDAO.selectAdmin(id);
    		        request.setAttribute("user", existingUser);
        List < User > listUser = userDAO.selectAllUsers();
        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("manageUser.jsp");
        dispatcher.include(request, response);
    }
    }
    private void showUserDashboard(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, ServletException, IOException {
    	
    	
    	    	if(request.getParameter("id")==null) {
    	    		request.setAttribute("error", "Invalid Id or Password");
    	    		RequestDispatcher dispatcher = request.getRequestDispatcher("loginPage.jsp");
    	            dispatcher.include(request, response);
    	    	}
    	    	else {
    	    	int id = Integer.parseInt(request.getParameter("id"));
    	    	System.out.println(123);
    	    	List < Test > listTest = userDAO.selectAllTests();
    	        request.setAttribute("listTest", listTest);
    	    	User existingUser = userDAO.selectUser(id);
    	        request.setAttribute("user", existingUser);
    	        RequestDispatcher dispatcher = request.getRequestDispatcher("userDashboard.jsp");
    	        dispatcher.include(request, response);
    	    }
    }
    
    private void showResultPage(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, ServletException, IOException {
    	
    	
    	    	if(request.getParameter("id")==null) {
    	    		request.setAttribute("error", "Invalid Id or Password");
    	    		RequestDispatcher dispatcher = request.getRequestDispatcher("loginPage.jsp");
    	            dispatcher.include(request, response);
    	    	}
    	    	else {
    	    	int id = Integer.parseInt(request.getParameter("id"));
    	    	List < Result > listResult = userDAO.selectUserResult(id);
    	        request.setAttribute("listResult", listResult);
    	    	User existingUser = userDAO.selectUser(id);
    	        request.setAttribute("user", existingUser);
    	        RequestDispatcher dispatcher = request.getRequestDispatcher("resultPage.jsp");
    	        dispatcher.include(request, response);
    	    }
    }
    
    private void showProfilePage(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, ServletException, IOException {
    	
    	
    	    	if(request.getParameter("id")==null) {
    	    		request.setAttribute("error", "Invalid Id or Password");
    	    		RequestDispatcher dispatcher = request.getRequestDispatcher("loginPage.jsp");
    	            dispatcher.include(request, response);
    	    	}
    	    	else {
    	    	int id = Integer.parseInt(request.getParameter("id"));
    	    	User existingUser = userDAO.selectUser(id);
    	        request.setAttribute("user", existingUser);
    	        String opass = request.getParameter("opass");
    	        String npass = request.getParameter("npass");
    	        String cpass = request.getParameter("cpass");
    	        
    	        if(opass!=null&&npass!=null&&cpass!=null) {
    	        	if(npass.equals(cpass)) {
    	        		User existingUserp = userDAO.selectUserPassword(id, opass);
        	    		if(existingUserp!=null){
        	    			userDAO.updateUserPass(id, npass);
        	    			request.setAttribute("success", "Password Changed Successfully");
        	    		}
        	    		else {
        	    			System.out.println(1);
        	    			request.setAttribute("error", "Wrong Password");
        	    		}
    	        	}
    	        	else {System.out.println(cpass+" "+npass);
    	        		request.setAttribute("error", "Password does not match");
    	        	}
    	        }
    	        
    	        
    	        
    	        
    	        RequestDispatcher dispatcher = request.getRequestDispatcher("profilePage.jsp");
    	        dispatcher.include(request, response);
    	    }
    }
    private void showExamPage(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, ServletException, IOException {
    	    	if(request.getParameter("id")==null||request.getParameter("topicid")==null) {
    	    		request.setAttribute("error", "Invalid Id or Password");
    	    		RequestDispatcher dispatcher = request.getRequestDispatcher("loginPage.jsp");
    	            dispatcher.include(request, response);
    	    	}
    	    	else {
    	    	int id = Integer.parseInt(request.getParameter("id"));
    	    	int topicid = Integer.parseInt(request.getParameter("topicid"));
    	    	int noq = userDAO.numberOfQuestions(topicid);
    	    	int cq = Integer.parseInt(request.getParameter("cq"));
    	    	int score= Integer.parseInt(request.getParameter("score"));
    	    	if(cq<noq) {
    	    		String topicname = userDAO.selectTopic(topicid);
        	    	List < Question > listQuestions = userDAO.selectAllQuestions(topicid);
        	    	 if(request.getParameter("answer")!=null) {
        	    		 if(request.getParameter("answer")==request.getParameter("coption")) {
        	    			 score =score+1;
        	    		 }
        	    	 }
        	    	request.setAttribute("listQuestions", listQuestions.get(cq));
        	    	User existingUser = userDAO.selectUser(id);
        	    	
        	        request.setAttribute("topicid", topicid);
        	        request.setAttribute("user", existingUser);
        	        request.setAttribute("topicname", topicname);
        	        request.setAttribute("noq", noq);
        	        request.setAttribute("cq", cq+1);
        	        request.setAttribute("score", score);
        	       
        	    	if(cq+1<noq) {
        	       	   	request.setAttribute("button", "Next");
        	       	}
        	       	else {
        	       		request.setAttribute("button", "Finish");
        	       	}
        	        RequestDispatcher dispatcher = request.getRequestDispatcher("examPage.jsp");
        	        dispatcher.include(request, response);
    	    	}
    	    	else if(cq==noq) {
    	    		String topicname = userDAO.selectTopic(topicid);
    	    		 if(request.getParameter("answer")!=null) {
    	    			 String answer = request.getParameter("answer");
    	    			 String coption = request.getParameter("coption");
    	    			 System.out.println(answer);
    	    			 System.out.println(coption);
        	    		 if(answer.equals(coption)) {
        	    			 score =score+1;
        	    			 System.out.println("1scored");
        	    		 }
        	    	 }
    	    		 User existingUser = userDAO.selectUser(id);
    	    		 request.setAttribute("user", existingUser);
         	        request.setAttribute("topicname", topicname);
        	        request.setAttribute("noq", noq);
        	        request.setAttribute("score", score);
        	        userDAO.insertResult(id, topicid, score, noq);
        	        RequestDispatcher dispatcher = request.getRequestDispatcher("testEnd.jsp");
        	        dispatcher.include(request, response);
    	    	}
    	    	
    	    }
    	    }
    private void showCreateTest(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException,ServletException, IOException {
    	if(request.getParameter("id")==null){
    		request.setAttribute("error", "Login to Continue");
    		RequestDispatcher dispatcher = request.getRequestDispatcher("loginPage.jsp");
            dispatcher.include(request, response);
    	}
    	else {
    		int ntid =0;
    		if(request.getParameter("ntname")!=null) {
    			String ntname =  request.getParameter("ntname");
    			ntid = userDAO.insertTopic(ntname);
    			request.setAttribute(ntname, null); 
    			request.setAttribute("ntid", ntid);
    		}
    	int id = Integer.parseInt(request.getParameter("id"));
    	User existingUser = userDAO.selectAdmin(id);
        request.setAttribute("user", existingUser);
   	 int noq  = Integer.parseInt(request.getParameter("noq"));
   	 int cq = Integer.parseInt(request.getParameter("cq"));
   	 if(request.getParameter("ntid")!=null){
   		 ntid = Integer.parseInt(request.getParameter("ntid"));
   		 }
   	 if(request.getParameter("que")!=null){
   	String que  = request.getParameter("que");
   	String opt1  = request.getParameter("opt1");
   	String opt2  = request.getParameter("opt2");
   	String opt3  = request.getParameter("opt3");
   	String opt4  = request.getParameter("opt4");
   	String ans   = request.getParameter("ans");
   	
   	userDAO.insertQuestion(que,opt1,opt2,opt3,opt4,ans,ntid);
   	request.setAttribute("status", "Question Added Succesfully");
   		 }
   	if(cq==noq) {

        showManageTests(request, response);
   	}
   	else {
   	request.setAttribute("ntid", ntid);
   	request.setAttribute("noq", noq);
   	request.setAttribute("cq", cq+1);
   	if(cq+1<noq) {
   	   	request.setAttribute("button", "Next");
   	}
   	else {
   		request.setAttribute("button", "Finish");
   	}
    	        RequestDispatcher dispatcher = request.getRequestDispatcher("createTest.jsp");
    	        dispatcher.include(request, response);
    	    }}
    }
    
    private void authentication(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException,ServletException , IOException {
    	System.out.println(1);
       
    			String idstring = request.getParameter("id");
    	    	 int id = Integer.parseInt(idstring);
    	    	String password = request.getParameter("password");
    	    	
    	    	String comparator= idstring.substring(0,1);
    	    	String admin="bleh";
    	    	switch(comparator) {
    	    	case "1":
    	    		admin="yes";
    	    		break;
    	    	case "2":
    	    		admin="no";
    	    		break;
    	    		
    	    	}
    	    	if(admin=="yes") {
    	    	
    	    		User existingUser = userDAO.selectAdminPassword(id, password);
    	    		if(existingUser!=null) {
       	    		 response.sendRedirect("adminHome?id="+id);
    	    		}
    	    		else {
    	    		 response.sendRedirect("");
    	    		}
    	    	}
    	    	else if(admin=="no") {
    	    		User existingUser = userDAO.selectUserPassword(id, password);
    	    		if(existingUser!=null) {	
  	    			 response.sendRedirect("userDashboard?id="+idstring);
       	    		}
       	    		else {
       	    		 response.sendRedirect("");
       	    		}
    	    		
    	    	}
    	    	else {
    	    		 response.sendRedirect("");
    	    	}
    	       
    	       
    	    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User existingUser = userDAO.selectUser(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        request.setAttribute("user", existingUser);
        dispatcher.forward(request, response);

    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
    	 int id = Integer.parseInt(request.getParameter("id"));
    	String name = request.getParameter("name");
        String email = request.getParameter("email");
        
        User newUser = new User(id, name, email);
        userDAO.insertUser(newUser);
        response.sendRedirect("list");
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        User book = new User(id, name, email);
        userDAO.updateUser(book);
        response.sendRedirect("list");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        userDAO.deleteUser(id);
        response.sendRedirect("list");
    }
}
