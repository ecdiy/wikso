package cn.net.dbi.test.dwr;

import java.sql.SQLException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.directwebremoting.json.types.JsonArray;
import org.springframework.beans.factory.annotation.Autowired;

import cn.net.dbi.test.model.Factor;
import cn.net.dbi.test.repository.TFactorRepository;
import cn.net.dbi.test.service.DBOpen;

@RemoteProxy(name = "dwrdiy")
public class Diy {

	@Autowired
	TFactorRepository factorRepository;
	@Autowired
	DBOpen db;

	@RemoteMethod
	public void save(long schemeId, String js) throws SQLException {
		JSONObject obj = JSONObject.fromObject(js);
		factorRepository.removeBySchemeId(schemeId);

		JSONArray arr = obj.getJSONArray("nodes");
		if (arr != null) {
			for (int i = 0; i < arr.size(); i++) {
				JSONObject json = arr.getJSONObject(i);
				Factor f = new Factor();
				f.setSchemeId(schemeId);
				f.setX(json.getInt("x"));
				f.setY(json.getInt("y"));
				f.setName(json.getString("text"));
				factorRepository.save(f);
			}

		}
		// JsonArray list = obj.get("");
	}
}
