package cn.net.dbi.test.web;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.net.dbi.test.model.Factor;
import cn.net.dbi.test.model.FactorRelation;
import cn.net.dbi.test.repository.FactorRelationRepository;
import cn.net.dbi.test.repository.TFactorRepository;
import cn.net.dbi.test.service.Convert;

@Controller
public class DataController {
	@Autowired
	ServletContext sc;
	@Autowired
	Convert convert;
	@Autowired
	TFactorRepository factorRepository;
	@Autowired
	FactorRelationRepository factorRelationRepository;

	@RequestMapping("data.jspa")
	public String designTable(
			@RequestParam(value = "id", defaultValue = "0") long sid,
			@RequestParam(value = "role", defaultValue = "0") String role,
			@RequestParam(value = "oid", defaultValue = "0") String oid,
			Model model) throws SQLException {
		List<FactorRelation> frAll = factorRelationRepository
				.findBySchemeId(sid);
		List<Factor> list;

		if (role.equals("1")) {
			list = convert.convertList(factorRepository.findNotLonely(sid, sid,
					sid));
		} else if (role.equals("2")) {
			list = convert.convertList(factorRepository.findByRelation(sid,
					oid, oid));
		} else {
			list = convert.convertList(factorRepository.findBySchemeId(sid));
		}

		String spo = ";,";
		for (Factor f : list) {
			spo += f.getId() + ",";
		}

		List<FactorRelation> frnew = new LinkedList<>();
		for (FactorRelation fr : frAll) {
			if (spo.indexOf("," + fr.getFid1() + ",") > 0
					&& spo.indexOf("," + fr.getFid2() + ",") > 0) {
				frnew.add(fr);
			}
		}

		model.addAttribute("list", list);
		model.addAttribute("frlist", frnew);
		return "data";
	}

	@RequestMapping("img.jspa")
	public String img(Model model) throws SQLException {
		return "img";
	}

	@RequestMapping("save.jspa")
	public String save(Model model) throws SQLException {
		System.out.println();

		return "data";
	}
}
