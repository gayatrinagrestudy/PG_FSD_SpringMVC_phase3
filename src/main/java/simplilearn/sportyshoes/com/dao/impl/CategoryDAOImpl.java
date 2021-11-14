package simplilearn.sportyshoes.com.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import simplilearn.sportyshoes.com.dao.CategoryDAO;
import simplilearn.sportyshoes.com.entity.Category;

@Repository
@Component
public class CategoryDAOImpl implements CategoryDAO {
	@Autowired
    private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public Category getCategoryById(long id) {
		String strId = String.valueOf(id);
		List<Category> list = this.sessionFactory.getCurrentSession().createQuery("from Category where id=" + strId).list();
		if (list.size() > 0)
			return (Category) list.get(0);
		else
			return null;
		
	}
	
	@SuppressWarnings("unchecked")
	public void updateCategory(Category category) {
		String sql = "";
		if (category.getID() == 0)
			this.sessionFactory.getCurrentSession().save(category);
		else if (category.getID() > 0) {
			sql = "update Category set name=:name where " +
					" ID=:id";
			Query query = this.sessionFactory.getCurrentSession().createQuery(sql);
			query.setParameter("name", category.getName());
			if (category.getID() > 0)
				query.setParameter("id", category.getID());
			
			query.executeUpdate();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public void deleteCategory(long id) {
		String sql = "delete from Category where ID=:id";
		Query query = this.sessionFactory.getCurrentSession().createQuery(sql);
		query.setParameter("id", id);
		query.executeUpdate();
		
	}

	@SuppressWarnings("unchecked")
	public List<Category> getAllCategories() {
		List<Category> list = this.sessionFactory.getCurrentSession().createQuery("from Category order by name").list();

		return list;
	}
	
}
