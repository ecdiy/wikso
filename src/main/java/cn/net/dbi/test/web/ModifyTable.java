package cn.net.dbi.test.web;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.net.dbi.test.DBOpen;

@Controller
public class ModifyTable {
	@Autowired
	DBOpen db;

	@RequestMapping("designTable.jspa")
	public String designTable(@RequestParam("tbl") String tbl, Model model)
			throws SQLException {
		model.addAttribute("cols", db.getTblColumns(tbl));
		return "designTable";
	}

	@RequestMapping("modifyTable.jspa")
	public String modifyTable(@RequestParam("sql") String sql,
			@RequestParam("tbl") String tbl) throws SQLException {
		db.exec(sql);
		return "redirect:designTable.jspa?tbl=" + tbl;
	}
}
