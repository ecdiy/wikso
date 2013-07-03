package cn.net.dbi.test.dwr;

import java.sql.SQLException;
import java.util.List;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;

import cn.net.dbi.test.model.Factor;
import cn.net.dbi.test.repository.SchemeRepository;
import cn.net.dbi.test.repository.TFactorRepository;
import cn.net.dbi.test.service.DBOpen;

@RemoteProxy(name = "dwra")
public class A {
	@Autowired
	TFactorRepository factorRepository;
	@Autowired
	SchemeRepository schemeRepository;
	@Autowired
	DBOpen db;

	@RemoteMethod
	public void savePoints(long schemeId, String points) {
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

	@RemoteMethod
	public void sqlExec(String sql) throws SQLException {
		db.exec(sql);
	}
}
