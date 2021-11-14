package simplilearn.sportyshoes.com.service;

import org.springframework.stereotype.Component;

import simplilearn.sportyshoes.com.entity.Admin;

//@Component
public interface AdminService {
	public Admin authenticate(String adminId, String pwd);
	public Admin getAdminById(long id);
	public void updatePwd(Admin admin);
}
