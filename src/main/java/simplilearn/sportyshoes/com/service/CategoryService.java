package simplilearn.sportyshoes.com.service;

import java.util.List;

import simplilearn.sportyshoes.com.entity.Category;


public interface CategoryService {

	 public Category getCategoryById(long id);
	 public void updateCategory(Category category);
	 public void deleteCategory(long id);
	 public List<Category> getAllCategories();
	 public String getCategoriesDropDown(long id);
	 
}
