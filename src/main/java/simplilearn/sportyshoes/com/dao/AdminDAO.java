package simplilearn.sportyshoes.com.dao;

import simplilearn.sportyshoes.com.entity.Admin;

public interface AdminDAO {
	public Admin authenticate(String adminId, String pwd);	
	public Admin getAdminById(long id);
	public void updatePwd(Admin admin);
}
