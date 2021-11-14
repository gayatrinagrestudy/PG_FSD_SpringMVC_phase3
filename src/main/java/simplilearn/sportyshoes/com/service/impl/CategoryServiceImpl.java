package simplilearn.sportyshoes.com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import simplilearn.sportyshoes.com.dao.CategoryDAO;
import simplilearn.sportyshoes.com.entity.Category;
import simplilearn.sportyshoes.com.service.CategoryService;

@Component
public class CategoryServiceImpl implements CategoryService {

	 @Autowired
	 private CategoryDAO categoryDAO;

	 @Transactional
	 public Category getCategoryById(long id) {
		 	return categoryDAO.getCategoryById(id);
		}
		
		
	 @Transactional
	 public void updateCategory(Category category) {
		 categoryDAO.updateCategory(category);
		}
		

	 @Transactional
	 public void deleteCategory(long id) {
		 categoryDAO.deleteCategory(id);
		}

	 @Transactional
	 public List<Category> getAllCategories() {
		 return categoryDAO.getAllCategories();
		}
		
	 @Transactional
	 public String getCategoriesDropDown(long id) {
		 StringBuilder sb = new StringBuilder("");
		 List<Category> list = categoryDAO.getAllCategories();
		 for(Category cat: list) {
			 if (cat.getID() == id)
				 sb.append("<option value=" + String.valueOf(cat.getID()) + " selected>" + cat.getName() + "</option>");
			 else
				 sb.append("<option value=" + String.valueOf(cat.getID()) + ">" + cat.getName() + "</option>");
				 
		 }
		 return sb.toString();
		}		 
}
