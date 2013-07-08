package cn.net.dbi.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import cn.net.dbi.test.model.Factor;

public interface TFactorRepository extends JpaRepository<Factor, Long> {

	public List<Factor> findBySchemeId(long schemeId);

	@Query(nativeQuery = true, value = "SELECT * FROM Factor WHERE schemeId=? AND id IN (SELECT fid1 FROM  FactorRelation WHERE schemeId=? UNION SELECT fid2 FROM  FactorRelation WHERE schemeId=?)")
	public List<Factor> findNotLonely(long schemeId, long schemeId2,
			long schemeId3);

	@Query(nativeQuery = true, value = "SELECT * FROM Factor WHERE schemeId=? AND id IN (SELECT fid1 FROM FactorRelation WHERE label=? UNION SELECT fid2 FROM FactorRelation WHERE label=? )")
	public List<Factor> findByRelation(long schemeId, String label,
			String label2);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM `Factor`  where  `schemeId`=?", nativeQuery = true)
	public int removeBySchemeId(long schemeId);

	@Modifying
	@Transactional
	@Query(value = "update `Factor` set toRelation=0,fromRelation=0,countRelation=0  where  `schemeId`=?", nativeQuery = true)
	public int initCountRelation(long schemeId);

	@Query(nativeQuery = true, value = "SELECT * FROM Factor WHERE schemeId=?1 AND countRelation IN (SELECT MAX(countRelation) FROM Factor WHERE schemeId=?1 limit 0,1)")
	public Factor findByMaxCountRelation(long schemeId);
}
