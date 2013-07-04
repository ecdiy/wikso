package cn.net.dbi.test.dwr;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;

import cn.net.dbi.test.model.Factor;
import cn.net.dbi.test.repository.TFactorRepository;

@RemoteProxy(name = "dwrdiy")
public class Diy {

	@Autowired
	TFactorRepository factorRepository;

	@RemoteMethod
	public void save(long schemeId, String js) {
		JSONObject obj = JSONObject.fromObject(js);
		factorRepository.removeBySschemeId(schemeId);
		JSONArray arr = obj.getJSONArray("nodes");
		for (int i = 0; i < arr.size(); i++) {
			JSONObject json = arr.getJSONObject(i);
			Factor f = new Factor();
			f.setSchemeId(schemeId);
			f.setX(json.getInt("x"));
			f.setY(json.getInt("y"));
			f.setName(json.getString("text"));
		}
	}

}
