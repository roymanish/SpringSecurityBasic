package com.maroy.security;

import java.security.Principal;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maroy.entity.EmployeeEntity;
import com.maroy.entity.UserEntity;
import com.maroy.service.EmployeeManager;
import com.maroy.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private EmployeeManager employeeManager;
	
	@Autowired
	private UserService userService;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		return "redirect:/list";
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String adminPage(Locale locale, Model model){
		
		model.addAttribute("employee", new EmployeeEntity());
		model.addAttribute("employeeList", employeeManager.getAllEmployees());
		return "editEmployeeList";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addEmployee(@ModelAttribute(value="employee") EmployeeEntity employee, BindingResult result) 
	{
		employeeManager.addEmployee(employee);
		return "redirect:/list";
	}

	@RequestMapping("/delete/{employeeId}")
	public String deleteEmplyee(@PathVariable("employeeId") Integer employeeId)
	{
		employeeManager.deleteEmployee(employeeId);
		return "redirect:/list";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        return "login";
    }
	
    @RequestMapping(value = "/accessdenied", method = RequestMethod.GET)
    public String loginerror(Model model) {
        model.addAttribute("error", "true");
        return "accessdenied";
    }
 
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model) {
        return "logout";
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
    	model.addAttribute("user", new UserEntity());
        return "register";
    }
    
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") UserEntity user, BindingResult result){
    	
    	userService.addUser(user);
    	
    	return "redirect:/login";
    }
	
	public void setEmployeeManager(EmployeeManager employeeManager) {
		this.employeeManager = employeeManager;
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
}
