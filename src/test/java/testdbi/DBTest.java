package testdbi;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.util.Assert;

import cn.net.dbi.test.repository.FactorRelationRepository;
import cn.net.dbi.test.repository.TFactorRepository;
import cn.net.dbi.test.service.DBOpen;
import cn.net.dbi.test.service.DBService;

@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class, TestInit.class })
@ContextConfiguration(locations = { "classpath*:dbi-test/*.xml" })
public class DBTest {
	@Autowired
	TFactorRepository tEntityRepository;
	@Autowired
	DBOpen db;
	@Autowired
	DBService ds;
	@Autowired
	FactorRelationRepository factorRelationRepository;

	@Test
	public void query() {
		List<String> list = factorRelationRepository.findLableBySchemeId(7);
		if (list != null)
			System.out.println(list.size());
	}

	@Test
	public void testInit() {
		Assert.notNull(tEntityRepository);
	}

	@Test
	public void testDBOpen() throws SQLException {
		// db.exec("ALTER TABLE `dbitest`.`TableName1`     ADD COLUMN `b2` VARCHAR(50) NULL ");
	}

	@Test
	public void testDBOpenQ() throws SQLException {
		db.getColumns();
	}

	@Test
	public void testDBOpenQ2() throws SQLException {
		// List<HashMap> list = db.getList(HashMap.class, "",
		// "select * from Factor where schemeId=?", 1);
	}

	// @Test
	// public void testJson() {
	// String str =
	// "{\"nodes\":[{\"x\":202,\"y\":241,\"text\":\"fa\",\"isAcceptState\":false},{\"x\":349,\"y\":239,\"text\":\"fa\",\"isAcceptState\":false}],"
	// +
	// "\"links\":[{\"type\":\"Link\",\"nodeA\":0,\"nodeB\":1,\"text\":\"fsd\",\"lineAngleAdjust\":0,\"parallelPart\":0.5,\"perpendicularPart\":0}]}";
	//
	// diy.save(4, str);
	// }

	@Autowired
	TFactorRepository factorRepository;

	@Test
	public void testDele() {
		factorRepository.removeBySchemeId(7);
	}

	@Test
	public void loadDate() throws IOException {
		File t = new File("src/test/resources/test.csv");
		ds.loadFromFile(10, t);
	}
}
