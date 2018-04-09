package fr.adaming.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IAdminDao;
import fr.adaming.model.Admin;

@Service("adService") // pour pouvoir l'utiliser avec managedProperty
@Transactional // pour rendre toutes les méthodes de la classe transactionnelles
public class AdminServiceImpl implements IAdminService {
	@Autowired
	private IAdminDao adminDao;
	
	// le setter pour l'injection de dépendance
	public void setAdminDao(IAdminDao adminDao) {
		this.adminDao = adminDao;
	}

	@Override
	public Admin isExist(Admin a) {
		return adminDao.isExist(a);
	}

}
