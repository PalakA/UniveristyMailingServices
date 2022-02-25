package com.mvc.universitymailservices;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.mvc.dao.PackagesDAO;
import com.mvc.dao.StudentDAO;
import com.mvc.pojo.Student;
import com.mvc.pojo.packages;
import com.mvc.pojo.user;

/**
 * Handles requests for the application home page.
 */
@Controller
public class StudentController {

	private static BCryptPasswordEncoder passwordEncorder = new BCryptPasswordEncoder();
	private final Logger logger = LoggerFactory.getLogger(StudentController.class);

	// FacilitiesCoordinator Dashboard
	@RequestMapping(value = "/studentHome", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView getStudentHomePage(HttpSession session, Map<String, Object> model,
			StudentDAO studentdao, PackagesDAO packagesdao) {

		user user = (user) session.getAttribute("student");
		model.put("student", user);
		Student student = studentdao.getStudentById(user.getUserid());
		List<packages> packages = PackagesDAO.getAllPackagesbyUserid(user.getUserid());

		ModelAndView m = new ModelAndView("studentHome");
		model.put("packages", packages);
		model.put("user", user);
		return m;
	}

	// GET Edit Profile
	@RequestMapping(value = "/studentHome/studentEditProfile", method = RequestMethod.GET, headers = "Accept=application/json")
	public ModelAndView getUserHistory(HttpSession session, HttpServletRequest request, ModelMap model,
			StudentDAO studentdao) {

		user user = (user) session.getAttribute("student");
		// Student student = studentdao.getStudentById(user.getUserid());

		model.put("student", user);
		model.put("user", user);
		ModelAndView m = new ModelAndView("studentEditProfile");
		return m;
	}

	// Update User Profile
	@RequestMapping(value = "/studentHome/studentEditProfile", method = RequestMethod.POST)
	public ModelAndView updateUser(HttpServletRequest request, ModelMap model, StudentDAO studentdao,
			SessionStatus status, HttpSession session) {

		String password = request.getParameter("password");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String phonenumber = request.getParameter("phonenumber");

		user user = (user) session.getAttribute("student");

		// Student student = studentdao.getStudentById(u.getUserid());

		if (password != null) {
			String hashedPassword = passwordEncorder.encode(password);
			studentdao.updateStudentDetails(hashedPassword, firstName, lastName, phonenumber, user);
			status.setComplete();
		} else {
			studentdao.updateStudentDetails(firstName, lastName, phonenumber, user);
			status.setComplete();
		}

		String msg = "User Account Updated Successfully";
		ModelAndView m = new ModelAndView("studentHome", "msg", msg);
		return m;
	}
}
