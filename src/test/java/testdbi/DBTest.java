package testdbi;

import java.sql.SQLException;
import java.util.HashMap;

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

import antlr.collections.List;
import cn.net.dbi.test.DBOpen;
import cn.net.dbi.test.repository.TFactorRepository;

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
//		List<HashMap> list = db.getList(HashMap.class, "",
//				"select * from Factor where schemeId=?", 1);
	}
}
