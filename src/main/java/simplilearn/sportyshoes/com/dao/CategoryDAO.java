package simplilearn.sportyshoes.com.dao;

import java.util.List;

import simplilearn.sportyshoes.com.entity.Category; 


public interface CategoryDAO {
	public Category getCategoryById(long id);	
	public void updateCategory(Category category);
	public void deleteCategory(long id);
	public List<Category> getAllCategories();
}
