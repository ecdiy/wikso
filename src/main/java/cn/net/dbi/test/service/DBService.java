package cn.net.dbi.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.net.dbi.test.repository.FactorAttrRepository;
import cn.net.dbi.test.repository.FactorRelationRepository;
import cn.net.dbi.test.repository.SchemeRepository;
import cn.net.dbi.test.repository.TFactorRepository;

@Component
public class DBService {

	@Autowired
	FactorRelationRepository factorRelationRepository;
	@Autowired
	TFactorRepository factorRepository;
	@Autowired
	SchemeRepository schemeRepository;
	@Autowired
	FactorAttrRepository factorAttrRepository;

	public void deleteScheme(long schemeId) {
		deleteFactor(schemeId);
		schemeRepository.delete(schemeId);
	}

	public void deleteFactor(long schemeId) {
		factorAttrRepository.removeBySchemeId(schemeId);
		factorRelationRepository.removeBySschemeId(schemeId);
		factorRepository.removeBySchemeId(schemeId); 
	}
}
