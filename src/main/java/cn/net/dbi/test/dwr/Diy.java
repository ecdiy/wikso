package cn.net.dbi.test.dwr;

import java.sql.SQLException;
import java.util.LinkedList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;

import cn.net.dbi.test.model.Factor;
import cn.net.dbi.test.model.FactorRelation;
import cn.net.dbi.test.repository.FactorRelationRepository;
import cn.net.dbi.test.repository.TFactorRepository;
import cn.net.dbi.test.service.DBService;

@RemoteProxy(name = "dwrdiy")
public class Diy {

	@Autowired
	TFactorRepository factorRepository;
	@Autowired
	FactorRelationRepository factorRelationRepository;
	@Autowired
	DBService ds;

	@RemoteMethod
	public void save(long schemeId, String js) throws SQLException {
		JSONObject obj = JSONObject.fromObject(js);
		ds.deleteFactor(schemeId);
		JSONArray arr = obj.getJSONArray("nodes");
		LinkedList<Factor> factorList = new LinkedList<>();
		if (arr != null) {
			for (int i = 0; i < arr.size(); i++) {
				JSONObject json = arr.getJSONObject(i);
				Factor f = new Factor();
				f.setSchemeId(schemeId);
				f.setX(json.getInt("x"));
				f.setY(json.getInt("y"));
				f.setName(json.getString("text"));
				factorRepository.save(f);
				factorList.add(f);
			}
		}
		JSONArray links = obj.getJSONArray("links");
		if (links != null) {
			for (int i = 0; i < links.size(); i++) {
				JSONObject json = links.getJSONObject(i);
				FactorRelation fr = new FactorRelation();
				fr.setSchemeId(schemeId);
				fr.setFid1(factorList.get(json.getInt("nodeA")).getId());
				fr.setFid2(factorList.get(json.getInt("nodeB")).getId());
				fr.setLabel(json.getString("text"));
				factorRelationRepository.save(fr);
			}
		}
	}
}
