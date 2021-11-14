package simplilearn.sportyshoes.com.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import simplilearn.sportyshoes.com.dao.AdminDAO;
import simplilearn.sportyshoes.com.entity.Admin;
import simplilearn.sportyshoes.com.service.AdminService;

@Service
@Component
public class AdminServiceImpl implements AdminService {
	
	 @Autowired
	 private AdminDAO adminDAO;

		@Transactional
		public Admin authenticate(String adminId, String pwd) {
			return adminDAO.authenticate(adminId, pwd);
		}
		
		@Transactional
		public Admin getAdminById(long id) {
			return adminDAO.getAdminById(id);
		}		
		
		@Transactional
		public void updatePwd(Admin admin) {
			adminDAO.updatePwd(admin);
		}

}
