package cn.net.dbi.test.web;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.net.dbi.test.model.Col;
import cn.net.dbi.test.model.Factor;
import cn.net.dbi.test.model.FactorRelation;
import cn.net.dbi.test.model.Scheme;
import cn.net.dbi.test.repository.FactorRelationRepository;
import cn.net.dbi.test.repository.SchemeRepository;
import cn.net.dbi.test.repository.TFactorRepository;
import cn.net.dbi.test.service.Convert;
import cn.net.dbi.test.service.DBOpen;
import cn.net.dbi.test.service.TreeConvert;

@Controller
public class SchemeController {
	private final static Logger logger = Logger
			.getLogger(SchemeController.class);
	@Autowired
	TFactorRepository factorRepository;

	@Autowired
	Convert convert;

	@Autowired
	FactorRelationRepository factorRelationRepository;
	@Autowired
	SchemeRepository schemeRepository;
	@Autowired
	DBOpen db;

	@Autowired
	TreeConvert treeConvert;

	@RequestMapping("addTblData.jspa")
	public String addTblData(@RequestParam("schemeId") long schemeId,
			@RequestParam("tbl") String tbl, HttpServletRequest req)
			throws SQLException {
		List<Col> cols = db.getTblColumns(tbl);
		StringBuffer sb1 = new StringBuffer("insert into ");
		sb1.append(tbl).append("(");
		StringBuffer sb2 = new StringBuffer();
		LinkedList<String> list = new LinkedList<>();
		for (Col col : cols) {

			String v = req.getParameter(col.getName());
			if (v != null && !v.isEmpty()) {
				sb1.append(col.getName()).append(",");
				sb2.append("?,");
				list.add(v);
			}
		}
		String sql = sb1.toString().substring(0, sb1.length() - 1) + ")values("
				+ sb2.toString().substring(0, sb2.length() - 1) + ")";
		logger.info(sql);
		db.exec(sql, list);

		return "redirect:scheme.jspa?id=" + schemeId;
	}

	@RequestMapping("tbl.jspa")
	public String tbl(@RequestParam("id") long schemeId,
			@RequestParam("tbl") String tbl, Model model) throws SQLException {
		model.addAttribute("cols", db.getTblColumns(tbl));
		try {
			model.addAttribute(
					"lists",
					db.getList(HashMap.class, "select * from " + tbl
							+ " where schemeId=?", schemeId));
		} catch (Exception e) {
			// logger.error("error table=：" + tbl + e.getMessage(), e);
			e.printStackTrace();
		}
		return "tbl";
	}

	@RequestMapping("addScheme.jspa")
	public String addScheme(@ModelAttribute("scheme") Scheme scheme) {
		scheme = schemeRepository.save(scheme);
		return "redirect:scheme.jspa?id=" + scheme.getId();
	}

	@RequestMapping("scheme.jspa")
	public String designTable(
			@RequestParam(value = "id", defaultValue = "0") long schemeId,
			@RequestParam(value = "role", defaultValue = "0") String role,
			@RequestParam(value = "oid", defaultValue = "0") String oid,
			Model model) throws Exception {
		if (oid != null)
			oid = new String(oid.getBytes("ISO8859-1"), "utf-8");
		Scheme scheme = schemeRepository.findOne(schemeId);
		if (scheme == null) {
			return "redirect:craeteScheme.jsp";
		}
		model.addAttribute("scheme", scheme);
		model.addAttribute("factorParams", db.getTblColumns("FactorParam"));
		model.addAttribute("factorRelations",
				db.getTblColumns("FactorRelation"));

		List<FactorRelation> frAll = factorRelationRepository
				.findBySchemeId(schemeId);
		List<Factor> list;

		if (role.equals("1")) {
			list = convert.convertList(factorRepository.findNotLonely(schemeId,
					schemeId, schemeId));
		} else if (role.equals("2")) {
			list = convert.convertList(factorRepository.findByRelation(
					schemeId, oid, oid));
		} else if (role.equals("3")) {// 正N边形
			list = convert.circle(factorRepository.findBySchemeId(schemeId),
					false);
		} else if (role.equals("4")) {// 树
		// list = convert.convertList(factorRepository.findByRelation(
		// schemeId, oid, oid));
			list = treeConvert.getTreeFacotr(schemeId).list;
		} else {
			list = convert.convertList(factorRepository
					.findBySchemeId(schemeId));
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
		// model.addAttribute("testva", "2");
		return "scheme";
	}

}
