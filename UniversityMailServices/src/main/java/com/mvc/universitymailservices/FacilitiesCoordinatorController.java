package com.mvc.universitymailservices;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.mvc.dao.FacilitiesCoordinatorDAO;
import com.mvc.dao.UserDAO;
import com.mvc.pojo.FacilitiesCoordinator;
import com.mvc.pojo.user;

/**
 * Handles requests for the application home page.
 */
@Controller
public class FacilitiesCoordinatorController {

	private static BCryptPasswordEncoder passwordEncorder = new BCryptPasswordEncoder();
	private final Logger logger = LoggerFactory.getLogger(FacilitiesCoordinatorController.class);

	// FacilitiesCoordinator Dashboard
	@RequestMapping(value = "/facilitiesCoordinatorHome", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView getFacilitiesCoordinatorHomePage(HttpSession session, Map<String, Object> model,
			FacilitiesCoordinatorDAO fCdao) {

		user user = (user) session.getAttribute("facilitiesCoordinator");
		FacilitiesCoordinator facilitiesCoordinator = fCdao.getUserById(user.getUserid());

		ModelAndView m = new ModelAndView("facilitiesCoordinatorHome");
		model.put("facilitiesCoordinator", facilitiesCoordinator);
		model.put("user", user);
		return m;
	}

	// Add User GET Request
	@RequestMapping(value = "/facilitiesCoordinatorHome/createEmployees", method = RequestMethod.GET)
	public ModelAndView viewAddUser(HttpSession session, Map<String, Object> model, FacilitiesCoordinatorDAO fCdao) {

		user user = (user) session.getAttribute("facilitiesCoordinator");
		FacilitiesCoordinator facilitiesCoordinator = fCdao.getUserById(user.getUserid());

		List<String> userrole = new ArrayList<String>();
		userrole.add("MailAssociate");
		userrole.add("MailRoomClerk");

		model.put("userrole", userrole);
		model.put("facilitiesCoordinator", facilitiesCoordinator);
		model.put("user", user);
		ModelAndView m = new ModelAndView("createEmployees");

		return m;
	}

	@RequestMapping(value = "/facilitiesCoordinatorHome/createEmployees", method = RequestMethod.POST)
	public ModelAndView AddUser(HttpSession session, HttpServletRequest request, UserDAO userdao,
			BindingResult result, ModelMap model, SessionStatus status) {

		String password = request.getParameter("password");
		String emailId = request.getParameter("email_address");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String phonenumber = request.getParameter("phonenumber");
		String mailroomnumber = request.getParameter("mailroomnumber");
		user user = (user) session.getAttribute("facilitiesCoordinator");

		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		boolean validEmail = emailId.matches(regex);

		if (!validEmail) {
			result.rejectValue("password", "user.emailId.invalid", "Please enter valid Email Id!");
		}

		if (result.hasErrors()) {
			String errmsg = "Please enter valid Email Id!";
			List<String> userrole = new ArrayList<String>();
			userrole.add("MailAssociate");
			userrole.add("MailRoomClerk");
			model.put("userrole", userrole);
			ModelAndView m = new ModelAndView("createEmployees", "errmsg", errmsg);
			return m;
		}

		String userrole = request.getParameter("userrole");
		String hashedPassword = passwordEncorder.encode(password);
		boolean bEmailExist = userdao.doUserExists(emailId);

		if (!bEmailExist) {

			status.setComplete();

			String errmsg = "Employee Already Exists.!";

			List<user> list = userdao.getAllUsers();
			model.put("users", list);

			List<String> userRole = new ArrayList<String>();
			userRole.add("MailAssociate");
			userRole.add("MailRoomClerk");
			model.put("userrole", userRole);

			ModelAndView m = new ModelAndView("createEmployees", "errmsg", errmsg);
			return m;

		} else {

			userdao.addUser(emailId, hashedPassword, userrole, firstName, lastName, phonenumber, mailroomnumber,null);

			status.setComplete();

			String msg = "Employee Added Successfully";

			List<user> list = userdao.getAllUsers();
			model.put("users", list);

			Email email = new HtmlEmail();

			email.setHostName("smtp.gmail.com");
			email.setSmtpPort(587);
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

			ModelAndView m = new ModelAndView("facilitiesCoordinatorHome", "msg", msg);
			return m;
		}
	}

	// GET Edit Profile
	@RequestMapping(value = "facilitiesCoordinatorHome/facilitiesCoordinatorEditProfile", method = RequestMethod.GET, headers = "Accept=application/json")
	public ModelAndView getUserHistory(HttpSession session, Map<String, Object> model, FacilitiesCoordinatorDAO fCdao) {

		user user = (user) session.getAttribute("facilitiesCoordinator");
		//FacilitiesCoordinator facilitiesCoordinator = fCdao.getUserById(user.getUserid());

		model.put("facilitiesCoordinator", user);
		model.put("user", user);
		ModelAndView m = new ModelAndView("facilitiesCoordinatorEditProfile");
		return m;
	}

	// Update User Profile
	@RequestMapping(value = "facilitiesCoordinatorHome/facilitiesCoordinatorEditProfile", method = RequestMethod.POST)
	public ModelAndView updateUser(HttpSession session, HttpServletRequest request, ModelMap model,
			SessionStatus status,FacilitiesCoordinatorDAO fCdao) {

		String password = request.getParameter("password");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String phonenumber = request.getParameter("phonenumber");

		user user = (user) session.getAttribute("facilitiesCoordinator");

		//FacilitiesCoordinator facilitiesCoordinator = fCdao.getUserById(u.getUserid());

		if (password != null) {
			String hashedPassword = passwordEncorder.encode(password);
			fCdao.updateDetails(hashedPassword, firstName, lastName, phonenumber, user);
			status.setComplete();
		} else {
			fCdao.updateDetails(firstName, lastName, phonenumber, user);
			status.setComplete();
		}

		String msg = "User Account Updated Successfully";
		ModelAndView m = new ModelAndView("facilitiesCoordinatorHome", "msg", msg);
		return m;
	}
}
