package testdbi;

import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

import cn.net.dbi.test.model.Factor;
import cn.net.dbi.test.repository.TFactorRepository;

public class TestInit extends AbstractTestExecutionListener {
	@Override
	public void prepareTestInstance(final TestContext testContext)
			throws Exception {
		// 测试框架,自动注入值.
		try {
			System.out.println("--test begin--"); 
			entityRepository = testContext.getApplicationContext().getBean(
					TFactorRepository.class);
			gold = getTEntity("金", 1);
			water = getTEntity("水", 2);
			earth = getTEntity("土", 3);
			fire = getTEntity("火", 4);
			wood = getTEntity("木", 5);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Factor gold, fire, water, wood, earth;
	static TFactorRepository entityRepository;

	private static Factor getTEntity(String name, long id) {
		Factor e = entityRepository.findOne(id);
		if (e == null) {
			e = new Factor();
			e.setFid(id);
			e.setName(name);
			entityRepository.save(e);
		}
		return e;
	}
}
