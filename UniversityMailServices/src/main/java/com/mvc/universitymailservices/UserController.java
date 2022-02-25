package com.mvc.universitymailservices;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.mvc.dao.UserDAO;
import com.mvc.pojo.user;

@Controller
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static BCryptPasswordEncoder passwordEncorder = new BCryptPasswordEncoder();

	// Login POST Request
	@RequestMapping(value = "/submitLogin", method = RequestMethod.POST)
	public ModelAndView loginProcess(HttpServletRequest request, UserDAO userdao, HttpSession session, ModelMap model,
			SessionStatus status) {

		String emailId = request.getParameter("emailid");
		String password = request.getParameter("password");

		user user = userdao.getUserByEmail(emailId);
		if (user == null) {

			String errmsg = "Please Enter Valid Email Address";
			ModelAndView mv = new ModelAndView("login", "errmsg", errmsg);
			return mv;
		} else {
			String role = user.getUserRole();
			String userstatus = user.getUserStatus();

			Boolean checkPassword = passwordEncorder.matches(password, user.getPassword());

			if (checkPassword) {

				if (role.equals("FacilitiesCoordinator")) {

					if (userstatus.equals("Active")) {

						userdao.updateLogin(user);
						session.setAttribute("facilitiesCoordinator", user);

						ModelAndView mv = new ModelAndView("redirect:/facilitiesCoordinatorHome");
						return mv;

					} else {

						String errmsg = "Account is InActive. Contact ResidentialMail@northeastern.edu";
						ModelAndView mv = new ModelAndView("login", "errmsg", errmsg);
						return mv;

					}

				} else if (role.equals("MailAssociate")) {

					if (userstatus.equals("Active")) {

						userdao.updateLogin(user);
						session.setAttribute("mailAssociate", user);

						ModelAndView mv = new ModelAndView("redirect:/mailAssociateHome");
						return mv;

					} else {

						String errmsg = "Account is InActive. Contact ResidentialMail@northeastern.edu";
						ModelAndView mv = new ModelAndView("login", "errmsg", errmsg);
						return mv;

					}

				} else if (role.equals("Student")) {

					if (userstatus.equals("Active")) {

						userdao.updateLogin(user);
						session.setAttribute("student", user);
						ModelAndView mv = new ModelAndView("redirect:/studentHome");
						return mv;

					} else {

						String errmsg = "Account is InActive. Contact ResidentialMail@northeastern.edu";
						ModelAndView mv = new ModelAndView("login", "errmsg", errmsg);
						return mv;

					}

				} else {

					if (userstatus.equals("Active")) {

						userdao.updateLogin(user);
						session.setAttribute("mailroomClerk", user);

						ModelAndView mv = new ModelAndView("redirect:/mailroomClerkHome");
						return mv;

					} else {

						String errmsg = "Account is InActive. Contact ResidentialMail@northeastern.edu";
						ModelAndView mv = new ModelAndView("login", "errmsg", errmsg);
						return mv;

					}
				}

			} else {

				String errmsg = "Login Failed.! Please try Again.";
				ModelAndView mv = new ModelAndView("login", "errmsg", errmsg);
				return mv;

			}
		}
	}

	// Register POST Request
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView registrationProcess(HttpSession session, HttpServletRequest request, UserDAO userdao,
			BindingResult result, ModelMap model, SessionStatus status) {

		user user = (user) session.getAttribute("user");

		String password = request.getParameter("password");
		String emailId = request.getParameter("email_address");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String mobileNumber = request.getParameter("phonenumber");
		String nuid = request.getParameter("neuid");

		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		boolean validEmail = emailId.matches(regex);

		if (!validEmail) {
			result.rejectValue("password", "user.emailId.invalid", "Please enter valid Email Id!");
		}

		if (result.hasErrors()) {

			ModelAndView m = new ModelAndView("register");
			return m;
		}

		boolean bEmailExist = userdao.doUserExists(emailId);

		if (!bEmailExist) {
			status.setComplete();

			String errmsg = "User Already Exists.!";
			ModelAndView m = new ModelAndView("register", "errmsg", errmsg);
			return m;
		} else {

			String userrole = "Student";
			String hashedPassword = passwordEncorder.encode(password);

			userdao.addUser(emailId, hashedPassword, userrole, firstName, lastName, mobileNumber, null,nuid);
			status.setComplete();

			String msg = "User Account Created Successfully";

			Email email = new HtmlEmail();

			email.setHostName("smtp.gmail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("residentialmailneu@gmail.com", "Password@123"));
			email.setSSLOnConnect(true);

			try {

				email.setFrom("residentialmailneu@gmail.com");
				email.setSubject("NeuMailServices :: Your Account has been Created");
				email.setMsg(" <h2>Account Details </h2>" + "<br>" + "<h3>Email ID: " + emailId + "</h3>"
						+ "<h3>Password: " + password + "</h3>" + "<span style:'color:red;'>");
				email.addTo(emailId);
				email.send();

			} catch (EmailException e) {

				e.printStackTrace();
			}

			ModelAndView m = new ModelAndView("login", "msg", msg);
			return m;
		}
	}

	// Logout
	@RequestMapping("/userLogout")
	public ModelAndView userLogout(HttpSession session) {
		session.invalidate();
		ModelAndView m = new ModelAndView("redirect:/");
		return m;

	}

}
