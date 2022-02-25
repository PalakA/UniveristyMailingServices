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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.mvc.dao.MailAssociateDAO;
import com.mvc.dao.PackagesDAO;
import com.mvc.pojo.MailAssociate;
import com.mvc.pojo.packages;
import com.mvc.pojo.user;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MailroomAssociateController {

	private static BCryptPasswordEncoder passwordEncorder = new BCryptPasswordEncoder();
	private final Logger logger = LoggerFactory.getLogger(MailroomAssociateController.class);

	// FacilitiesCoordinator Dashboard
	@RequestMapping(value = "/mailAssociateHome", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView getFacilitiesCoordinatorHomePage(HttpSession session, Map<String, Object> model,
			MailAssociateDAO maildao) {

		user user = (user) session.getAttribute("mailAssociate");
		MailAssociate mailAssociate = maildao.getUserById(user.getUserid());

		ModelAndView m = new ModelAndView("mailAssociateHome");
		model.put("mailAssociate", mailAssociate);
		model.put("user", user);
		return m;
	}

	@RequestMapping(value = "/mailAssociateHome", method = RequestMethod.POST)
	public ModelAndView viewStudentPackages(HttpServletRequest request, Map<String, Object> model,
			PackagesDAO packagesdao, MailAssociateDAO maildao, HttpSession session) {

		user user = (user) session.getAttribute("mailAssociate");
		String otp = request.getParameter("otp");
		List<packages> packages = PackagesDAO.getAllPackagesbyOtp(otp);

		ModelAndView m = new ModelAndView("studentPackagesView", "packages", packages);
		model.put("mailAssociate", user);
		model.put("otp", otp);
		return m;
	}

	@RequestMapping(value = "/DeliveredStudentPackages/{packageId}/{otp}", method = RequestMethod.GET)
	public ModelAndView attendOpdPatient(@PathVariable int packageId, @PathVariable String otp, HttpSession session,
			HttpServletRequest request, Map<String, Object> model, PackagesDAO packagesdao, MailAssociateDAO maildao) {

		// int packageId = Integer.parseInt(request.getParameter("packageId"));
		user user = (user) session.getAttribute("mailAssociate");
		MailAssociate mailAssociate = maildao.getUserById(user.getUserid());

		packagesdao.updatePackageDelivery(packageId);
		String msg = "Package Details Updated Successfully";

		ModelAndView m = new ModelAndView("mailAssociateHome");
		model.put("msg", msg);
		model.put("mailAssociate", mailAssociate);
		model.put("user", user);
		return m;
	}

	// GET Edit Profile
	@RequestMapping(value = "mailAssociateHome/mailAssociateEditProfile", method = RequestMethod.GET, headers = "Accept=application/json")
	public ModelAndView getUserHistory(HttpSession session, Map<String, Object> model, MailAssociateDAO maildao) {

		user user = (user) session.getAttribute("mailAssociate");
		//MailAssociate mailAssociate = maildao.getUserById(user.getUserid());

		ModelAndView m = new ModelAndView("mailAssociateEditProfile");
		model.put("mailAssociate", user);
		model.put("user", user);
		return m;
	}

	// Update User Profile
	@RequestMapping(value = "mailAssociateHome/mailAssociateEditProfile", method = RequestMethod.POST)
	public ModelAndView updateUser(HttpSession session,HttpServletRequest request, ModelMap model, MailAssociateDAO maildao,
			SessionStatus status) {

		String password = request.getParameter("password");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String phonenumber = request.getParameter("phonenumber");

		user user = (user) session.getAttribute("mailAssociate");

		//MailAssociate mailAssociate = maildao.getUserById(u.getUserid());

		if (password != null) {
			String hashedPassword = passwordEncorder.encode(password);
			maildao.updateDetails(hashedPassword, firstName, lastName, phonenumber, user);
			status.setComplete();
		} else {
			maildao.updateDetails(firstName, lastName, phonenumber, user);
			status.setComplete();
		}

		String msg = "User Account Updated Successfully";
		ModelAndView m = new ModelAndView("mailAssociateHome", "msg", msg);
		return m;
	}
}
