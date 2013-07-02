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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.net.dbi.test.DBOpen;
import cn.net.dbi.test.model.Factor;
import cn.net.dbi.test.model.Scheme;
import cn.net.dbi.test.repository.SchemeRepository;
import cn.net.dbi.test.repository.TFactorRepository;

@Controller
public class XController {
	private final static Logger logger = Logger.getLogger(XController.class);
	@Autowired
	TFactorRepository factorRepository;

	@Autowired
	SchemeRepository schemeRepository;
	@Autowired
	DBOpen db;

	@RequestMapping("addTblData.jspa")
	public String addTblData(@RequestParam("schemeId") long schemeId,
			@RequestParam("tbl") String tbl, HttpServletRequest req)
			throws SQLException {
		List<HashMap<String, String>> cols = db.getTblColumns(tbl);
		StringBuffer sb1 = new StringBuffer("insert into ");
		sb1.append(tbl).append("(");
		StringBuffer sb2 = new StringBuffer();
		LinkedList<String> list = new LinkedList<>();
		for (HashMap<String, String> col : cols) {
			String n = col.get("name");
			String v = req.getParameter(n);
			if (v != null && !v.isEmpty()) {
				sb1.append(n).append(",");
				sb2.append("?,");
				list.add(v);
			}
		}
		String sql = sb1.toString().substring(0, sb1.length() - 1) + ")values("
				+ sb2.toString().substring(0, sb2.length() - 1) + ")";
		System.out.println(sql);
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
			logger.error("error table=：" + tbl + "\n" + e.getMessage(), e);
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
	public String scheme(@RequestParam("id") long schemeId, Model model)
			throws SQLException {
		Scheme scheme = schemeRepository.findOne(schemeId);
		if (scheme == null) {
			return "redirect:craeteScheme.jsp";
		}
		model.addAttribute("scheme", scheme);

		model.addAttribute("factorParams", db.getTblColumns("FactorParam"));
		model.addAttribute("factorRelations",
				db.getTblColumns("FactorRelation"));

		return "scheme";
	}

	@ResponseBody
	@RequestMapping("updatePoints.jspa")
	public void updatePoints(@RequestParam("id") long schemeId,
			@RequestParam("points") String points) {
		List<Factor> list = factorRepository.findBySchemeId(schemeId);
		String pos[] = points.split(";");
		for (int i = 0; i < list.size(); i++) {
			String xy = pos[i];
			String p[] = xy.split(",");
			list.get(i).setX(Integer.parseInt(p[0]));
			list.get(i).setY(Integer.parseInt(p[1]));
			factorRepository.save(list.get(i));
		}

	}

	// modifyTable.jspa
}
