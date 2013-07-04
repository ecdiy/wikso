package cn.net.dbi.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.net.dbi.test.repository.TFactorRepository;

@Component
public class DBService {

	@Autowired
	TFactorRepository factorRepository;

	public void deleteScheme(long scheme) {
	}
}
