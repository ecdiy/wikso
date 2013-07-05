package cn.net.dbi.test.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.net.dbi.test.model.Factor;
import cn.net.dbi.test.model.FactorRelation;
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

	public void loadFromFile(long schemeId, File file) throws IOException {
		String cot = FileUtils.readFileToString(file, "GBK");
		String pp[] = cot.trim().split("\r\n");
		// ==
		deleteFactor(schemeId);
		// ===
		HashMap<String, Factor> map = new HashMap<>();
		LinkedList<Factor> list = new LinkedList<>();
		String fstr[] = pp[0].split("\t");
		for (String s : fstr) {
			if (s == null || s.trim().isEmpty() || map.containsKey(s))
				continue;
			Factor f = new Factor();
			f.setSchemeId(schemeId);
			f.setName(s);
			f.setX(0);
			f.setY(0);
			map.put(s, f);
			list.addLast(f);
			factorRepository.save(f);
		}
		// ===
		for (int i = 1; i < pp.length; i++) {
			String tds[] = pp[i].trim().split("\t");
			Factor f1 = map.get(tds[0]);
			if (f1 == null)
				continue;
			for (int j = 0; j < list.size(); j++) {
				FactorRelation fr = new FactorRelation();
				fr.setFid1(f1.getId());
				fr.setFid2(list.get(j).getId());
				if (fr.getFid1() == fr.getFid2())
					continue;
				fr.setSchemeId(schemeId);
				fr.setLabel(tds[j + 1]);
				factorRelationRepository.save(fr);
			}
		}

	}
}
