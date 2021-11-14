package simplilearn.sportyshoes.com.controller;



import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import simplilearn.sportyshoes.com.entity.Admin;
import simplilearn.sportyshoes.com.entity.Category;
import simplilearn.sportyshoes.com.service.AdminService;
import simplilearn.sportyshoes.com.service.CategoryService;

@Controller
public class AdminController {
	
	@Autowired
    private AdminService adminService; 
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value = "/adminloginaction", method = RequestMethod.POST)
    public String loginAction(ModelMap map, javax.servlet.http.HttpServletRequest request, 
    		 @RequestParam(value="admin_id", required=true) String adminId,
    		 @RequestParam(value="admin_pwd", required=true) String adminPwd) 
    {
	  Admin admin = adminService.authenticate(adminId, adminPwd);
	  if (admin == null) { 
		  map.addAttribute("error", "Admin login failed");
		  return "redirect:admin-login";
	  }
	  HttpSession session = request.getSession();
	  session.setAttribute("admin_id", admin.getId());
	  
        return  "redirect:admincategories"; 
    }	
	
	@RequestMapping(value = "/adminchangepassword", method = RequestMethod.GET)
    public String changePwd(ModelMap map, javax.servlet.http.HttpServletRequest request) 
    {
	  HttpSession session = request.getSession();
	  if (session.getAttribute("admin_id") == null) {
		  return "redirect:admin-login";
	  }
	  
	  Admin admin = adminService.getAdminById((Long)session.getAttribute("admin_id")); 
	  
	  map.addAttribute("admin", admin);
      return "admin/change-password"; 
    }
	
	 @RequestMapping(value = "/adminchangepwdaction", method = RequestMethod.POST)
	    public String updatePassword(ModelMap map,  @RequestParam(value="pwd", required=true) String pwd,
	    		 @RequestParam(value="pwd2", required=true) String pwd2, 
	    		 javax.servlet.http.HttpServletRequest request)
	    {
		  HttpSession session = request.getSession();
		  if (session.getAttribute("admin_id") == null) {
			  return "redirect:admin-login";
		  }		  
		  
		  if (pwd == null || pwd2 == null || pwd.equals("") || pwd2.equals("")) {
			  map.addAttribute("error", "Error , Incomplete passwords submitted.");
			  return "admin/change-password";
		  }
		  if (!pwd.equals(pwd2)) {
			  map.addAttribute("error", "Error , Passwords do not match.");
			  return "admin/change-password";
		  }
		  Admin admin = adminService.getAdminById((Long)session.getAttribute("admin_id")); 
		  admin.setPassord(pwd);
		  adminService.updatePwd(admin);
		  
	        return "redirect:admin-login";  
	    }	
	 
	 @RequestMapping(value = "/admincategories", method = RequestMethod.GET)
	    public String categories(ModelMap map, javax.servlet.http.HttpServletRequest request) 
	    {
		  HttpSession session = request.getSession();
		  if (session.getAttribute("admin_id") == null) {
			  return "redirect:admin-login";
		  } 
		  
		  List<Category> list = categoryService.getAllCategories();
		  map.addAttribute("list", list);
          return "admin/categories"; 
	    }
	 
	  @RequestMapping(value = "/admindeletecat",  method = RequestMethod.GET)
	    public String deleteCategory(ModelMap map,  @RequestParam(value="id", required=true) String id,
	    		javax.servlet.http.HttpServletRequest request) 
	    {
		  HttpSession session = request.getSession();
		  if (session.getAttribute("admin_id") == null) {
			  return "redirect:admin-login";
		  }
		  long idValue = Long.parseLong(id);
		  Category category = new Category();
		  if (idValue > 0) {
			  categoryService.deleteCategory(idValue);
		  }
		  return "redirect:admincategories";
	    }	
	  
	  
	  @RequestMapping(value = "/admineditcat",  method = RequestMethod.GET)
	    public String editCategory(ModelMap map,  @RequestParam(value="id", required=true) String id,
	    		javax.servlet.http.HttpServletRequest request) 
	    {
		  HttpSession session = request.getSession();
		  if (session.getAttribute("admin_id") == null) {
			  return "redirect:admin-login";
		  }
		  long idValue = Long.parseLong(id);
		  Category category = new Category();
		  if (idValue > 0) {
			  category = categoryService.getCategoryById(idValue);
		  } else {
			  category.setID(0);
		  }
		  map.addAttribute("category", category);
	        return "admin/edit-category"; 
	    }		  

	  
	  @RequestMapping(value = "/adminaddeditaction", method = RequestMethod.POST)
	    public String addOrEditCategory(ModelMap map,  @RequestParam(value="id", required=true) String id,
	    		 @RequestParam(value="name", required=true) String name, 
	    		 javax.servlet.http.HttpServletRequest request)
	    {
		  long idValue = Long.parseLong(id); 
		  
		  if (name == null || name.equals("") ) { 
			  map.addAttribute("error", "Error, A category name must be specified");
			  return "redirect:admineditcat?id="+id;
		  }
		  	Category category = new Category();
		  	category.setID(idValue); 
		  	category.setName(name);
		  	
		  	categoryService.updateCategory(category); 		  	
	        return "redirect:admincategories";  
	    }

}
