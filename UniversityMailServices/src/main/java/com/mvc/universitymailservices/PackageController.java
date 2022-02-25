package com.mvc.universitymailservices;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mvc.dao.PackagesDAO;
import com.mvc.pojo.packages;
import com.mvc.pojo.user;

@Controller
public class PackageController {

	private final Logger logger = LoggerFactory.getLogger(PackageController.class);

	// Add User GET Request
	@RequestMapping(value = "/facilitiesCoordinatorHome/mailroom", method = RequestMethod.GET)
	public ModelAndView viewMailroom(HttpServletRequest request,Map<String, Object> model, PackagesDAO packagesdao, @ModelAttribute("user") user user) {

		List<packages> packages = PackagesDAO.getAllPackages();

		ModelAndView m = new ModelAndView("mailroomView", "packages", packages);
		return m;
	}

}
