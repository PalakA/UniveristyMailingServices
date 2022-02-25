package com.mvc.universitymailservices;

import java.util.Map;
import java.util.Random;

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

import com.mvc.dao.MailClerkDAO;
import com.mvc.dao.PackagesDAO;
import com.mvc.dao.UserDAO;
import com.mvc.pojo.MailroomClerk;
import com.mvc.pojo.user;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MailroomClerkController {

	private static BCryptPasswordEncoder passwordEncorder = new BCryptPasswordEncoder();
	private final Logger logger = LoggerFactory.getLogger(MailroomClerkController.class);

	// FacilitiesCoordinator Dashboard
	@RequestMapping(value = "/mailroomClerkHome", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView getFacilitiesCoordinatorHomePage(HttpSession session, Map<String, Object> model,
			MailClerkDAO mailclerkdao) {

		user user = (user) session.getAttribute("mailroomClerk");
		MailroomClerk mailroomClerk = mailclerkdao.getUserById(user.getUserid());

		ModelAndView m = new ModelAndView("mailroomClerkHome");
		model.put("mailroomClerk", mailroomClerk);
		model.put("user", user);
		return m;
	}

	// Add User GET Request
	@RequestMapping(value = "/mailroomClerkHome/addNewPackages", method = RequestMethod.GET)
	public ModelAndView viewAddUser(HttpSession session, Map<String, Object> model, MailClerkDAO mailclerkdao) {

		user user = (user) session.getAttribute("mailroomClerk");
		// MailroomClerk mailroomClerk = mailclerkdao.getUserById(user.getUserid());

		model.put("mailroomClerk", user);
		model.put("user", user);

		ModelAndView m = new ModelAndView("addNewPackages");
		return m;
	}

	@RequestMapping(value = "/mailroomClerkHome/addNewPackages", method = RequestMethod.POST)
	public ModelAndView AddPackage(HttpSession session, HttpServletRequest request, UserDAO userdao,
			PackagesDAO packagesdao, BindingResult result, ModelMap model, SessionStatus status,
			MailClerkDAO mailclerkdao) {

		String packageDesc = request.getParameter("packageDesc");
		String aisleNumber = request.getParameter("aisleNumber");
		String mailRoomNumber = request.getParameter("mailRoomNumber");
		String nuid = request.getParameter("neuid");

		user user = (user) session.getAttribute("mailroomClerk");

		String notifyStudent = "Yes";
		String clerkName = "";

		Random randomGenerator = new Random();
		long otp = randomGenerator.nextInt(10000);

		if ((user.getFirstName()) == null) {
			clerkName = "Mail Associate";
		} else {
			clerkName = user.getFirstName();
		}

		packagesdao.addPackage(packageDesc, aisleNumber, mailRoomNumber, clerkName, nuid, notifyStudent, otp, user);
		mailclerkdao.updatepackageUserId(nuid);

		status.setComplete();

		String msg = "Package Added Successfully";

		if (notifyStudent.equals("Yes")) {
			Email email = new HtmlEmail();

			email.setHostName("smtp.gmail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("residentialmailneu@gmail.com", "Password@123"));
			email.setSSLOnConnect(true);

			try {

				email.setFrom("residentialmailneu@gmail.com");
				email.setSubject("NeuMailServices :: Your Package is ready for pickup");
				email.setMsg(" <h2>Account Details </h2>" + "<br>" + "<h3>Your OTP to pickup packages are: " + otp
						+ "</h3>" + "<span style:'color:red;'>");
				email.addTo("residentialmailneu@gmail.com");
				email.send();

			} catch (EmailException e) {

				e.printStackTrace();
			}
		}

		ModelAndView m = new ModelAndView("mailroomClerkHome", "msg", msg);
		return m;
	}

	// GET Edit Profile
	@RequestMapping(value = "mailroomClerkHome/mailClerkEditProfile", method = RequestMethod.GET, headers = "Accept=application/json")
	public ModelAndView getUserHistory(HttpSession session, Map<String, Object> model, MailClerkDAO mailclerkdao) {

		user user = (user) session.getAttribute("mailroomClerk");
		MailroomClerk mailroomClerk = mailclerkdao.getUserById(user.getUserid());

		model.put("mailroomClerk", mailroomClerk);
		model.put("user", user);
		ModelAndView m = new ModelAndView("mailClerkEditProfile");
		return m;
	}

	// Update User Profile
	@RequestMapping(value = "mailroomClerkHome/mailClerkEditProfile", method = RequestMethod.POST)
	public ModelAndView updateUser(HttpServletRequest request, ModelMap model, SessionStatus status,
			MailClerkDAO mailclerkdao, HttpSession session) {

		String password = request.getParameter("password");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String phonenumber = request.getParameter("phonenumber");

		user user = (user) session.getAttribute("mailroomClerk");

		// MailroomClerk mailroomClerk = mailclerkdao.getUserById(u.getUserid());

		if (password != null) {
			String hashedPassword = passwordEncorder.encode(password);
			mailclerkdao.updateDetails(hashedPassword, firstName, lastName, phonenumber, user);
			status.setComplete();
		} else {
			mailclerkdao.updateDetails(firstName, lastName, phonenumber, user);
			status.setComplete();
		}

		String msg = "User Account Updated Successfully";
		ModelAndView m = new ModelAndView("mailroomClerkHome", "msg", msg);
		return m;
	}

}
