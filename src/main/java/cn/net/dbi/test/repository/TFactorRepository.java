package cn.net.dbi.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.net.dbi.test.model.Factor;

public interface TFactorRepository extends
		PagingAndSortingRepository<Factor, Long> {

	public List<Factor> findBySchemeId(long schemeId);

	@Query(nativeQuery = true, value = "SELECT * FROM Factor WHERE schemeId=? AND id IN (SELECT fid1 FROM  FactorRelation WHERE schemeId=? UNION SELECT fid2 FROM  FactorRelation WHERE schemeId=?)")
	public List<Factor> findNotLonely(long schemeId, long schemeId2,
			long schemeId3);

	@Query(nativeQuery = true, value = "SELECT * FROM Factor WHERE schemeId=? AND id IN (SELECT fid1 FROM FactorRelation WHERE label=? UNION SELECT fid2 FROM FactorRelation WHERE label=? )")
	public List<Factor> findByRelation(long schemeId, String label, String label2);

	@Query(nativeQuery = true, value = "delete from Factor where schemeId=?")
	public void removeBySschemeId(long schemeId);
}
