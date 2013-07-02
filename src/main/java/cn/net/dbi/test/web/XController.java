package cn.net.dbi.test.web;

import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import antlr.collections.List;
import cn.net.dbi.test.DBOpen;
import cn.net.dbi.test.model.Scheme;
import cn.net.dbi.test.repository.SchemeRepository;
import cn.net.dbi.test.repository.TFactorRepository;

@Controller
public class XController {

	@Autowired
	TFactorRepository factorRepository;

	@Autowired
	SchemeRepository schemeRepository;
	@Autowired
	DBOpen db;

	@RequestMapping("addScheme.jspa")
	public String addScheme(@ModelAttribute("scheme") Scheme scheme) {
		scheme = schemeRepository.save(scheme);
		return "redirect:scheme.jspa?id=" + scheme.getId();
	}

	@RequestMapping("scheme.jspa")
	public String scheme(@RequestParam("id") long schemeId, Model model)
			throws SQLException {
		Scheme scheme = schemeRepository.findOne(schemeId);
		if (scheme == null) {
			return "redirect:craeteScheme.jsp";
		}
		model.addAttribute("scheme", scheme);

		model.addAttribute("factorCols", db.getTblColumns("Factor"));
		model.addAttribute("factors", db.getList(HashMap.class,
				"select * from Factor where schemeId=?", schemeId));

		model.addAttribute("factorParams", db.getTblColumns("FactorParam"));
		model.addAttribute("factorRelations",
				db.getTblColumns("FactorRelation"));

		return "scheme";
	}

	// modifyTable.jspa
}
