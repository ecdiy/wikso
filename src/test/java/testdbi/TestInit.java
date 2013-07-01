package testdbi;

import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

import cn.net.dbi.test.model.Factor;
import cn.net.dbi.test.model.FactorRelation;
import cn.net.dbi.test.repository.FactorRelationRepository;
import cn.net.dbi.test.repository.TFactorRepository;

public class TestInit extends AbstractTestExecutionListener {
	@Override
	public void prepareTestInstance(final TestContext testContext)
			throws Exception {
		try {
			System.out.println("--test begin--");
			entityRepository = testContext.getApplicationContext().getBean(
					TFactorRepository.class);
			gold = getTEntity("金", 1);
			water = getTEntity("水", 2);
			earth = getTEntity("土", 3);
			fire = getTEntity("火", 4);
			wood = getTEntity("木", 5);

			factorRelationRepository = testContext.getApplicationContext()
					.getBean(FactorRelationRepository.class);
			initRData();
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

	static FactorRelationRepository factorRelationRepository;

	private static void initRData() {
		add(gold, water, "泄");
		add(gold, wood, "泄");
		add(gold, fire, "泄");
		add(gold, earth, "泄");
		
 
		add(water, wood, "生");
		add(water, fire, "泄");
		add(water, earth, "生");
		
 
		add(wood, fire, "泄");
		add(wood, earth, "生"); 
 
		add(fire, earth, "泄");
	}

	private static void add(Factor fid1, Factor fid2, String label) {
		FactorRelation fr = factorRelationRepository.findByFid1AndFid2AndLabel(
				fid1.getFid(), fid2.getFid(), label);
		if (fr == null) {
			fr = new FactorRelation();
			fr.setFid1(fid1.getFid());
			fr.setFid2(fid2.getFid());
			fr.setLabel(label);
			factorRelationRepository.save(fr);
		}
	}

}
