package cn.net.dbi.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.net.dbi.test.model.Col;
import cn.net.dbi.test.model.Factor;
import cn.net.dbi.test.model.FactorAttr;
import cn.net.dbi.test.repository.FactorAttrRepository;
import cn.net.dbi.test.repository.TFactorRepository;

@Component
public class Convert {
	@Autowired
	TFactorRepository factorRepository;
	@Autowired
	FactorAttrRepository factorAttrRepository;

	public List<Factor> convertList(List<Factor> list) {
		int needCovert = 0;
		for (Factor f : list) {
			if (f.getX() == 0)
				needCovert++;
		}
		if (needCovert >= 1) {
			for (int i = 0; i < list.size(); i++) {
				Factor f = list.get(i);
				f.setX(50 + (int) (250 * (1 + Math.sin(Math.PI * i * 2
						/ list.size()))));
				f.setY(50 + (int) (250 * (1 - Math.cos(Math.PI * i * 2
						/ list.size()))));
				factorRepository.save(f);
			}
		}
		return list;
	}

	private String df(Col col, String v) {
		return "<div class=\"field field-first\"><div class=\"label\">"
				+ "	<label for=\"input\">" + col.getName()
				+ ":</label></div><div class=\"input\">" + v + "</div></div>";
	}

	public String getDefHtml(Col col, long schemeId) {
		if (col.getTbl().equals("FactorRelation")
				&& col.getName().equals("label")) {
			List<FactorAttr> list = factorAttrRepository
					.findBySchemeId(schemeId);
			StringBuffer sb = new StringBuffer();
			if (list != null && list.size() > 1) {
				sb.append("<select  id=\"" + col.getName() + "\" name=\""
						+ col.getName() + "\">");
				for (FactorAttr f : list) {
					sb.append("<option value='").append(f.getLabel())
							.append("'>").append(f.getLabel())
							.append("</option>");
				}
				sb.append("</select>");
			}
			return df(col, sb.toString());
		}
		if (col.getName().equals("x") || col.getName().equals("y")) {
			return "<input type=\"hidden\" name=\"" + col.getName()
					+ "\" value=\"0\">";
		} else if (col.getName().equals("fid1") || col.getName().equals("fid2")) {
			List<Factor> list = factorRepository.findBySchemeId(schemeId);
			StringBuffer sb = new StringBuffer();
			if (list != null && list.size() > 1) {
				sb.append("<select  id=\"" + col.getName() + "\" name=\""
						+ col.getName() + "\">");
				for (Factor f : list) {
					sb.append("<option value='").append(f.getId()).append("'>")
							.append(f.getName()).append("</option>");
				}
				sb.append("</select>");
			}
			return df(col, sb.toString());
		} else {
			return df(col, "<input type=\"text\" id=\"" + col.getName()
					+ "\" name=\"" + col.getName() + "\" />");
		}
	}

}
