package com.mvc.universitymailservices;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mvc.dao.UserDAO;
import com.mvc.pojo.user;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
	private final Logger logger = LoggerFactory.getLogger(HomeController.class);

	// HomePage
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView goHome(UserDAO userdao) {

		user user = userdao.getUserByEmail("facilitiescoordinator@gmail.com");
		if(user==null)
		{
			String password = "Password";
			String hashedPassword = passwordEncoder.encode(password);
			userdao.addAdminUser(hashedPassword);
		}

		ModelAndView model = new ModelAndView("home");
		return model;
	}

	// HomePage
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView getHomePage(HttpSession session) {
		user user = (user) session.getAttribute("user");
		if (user != null) {
			ModelAndView model = new ModelAndView("home");
			model.addObject("user", user);
			return model;
		} else {
			ModelAndView model = new ModelAndView("redirect:/");
			return model;
		}
	}

	// GET Login Page
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView getLogin(ModelMap model) {
		user user = new user(); // FormBackingObject
		model.addAttribute("user", user);
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}

	// GET Sign Up Page Request
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView getRegister(ModelMap model,@ModelAttribute("user") user user) {
		model.addAttribute("user", user);
		ModelAndView mv = new ModelAndView("register");
		return mv;
	}
}
