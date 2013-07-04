package cn.net.dbi.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import cn.net.dbi.test.model.FactorRelation;

public interface FactorRelationRepository extends
		PagingAndSortingRepository<FactorRelation, Long> {

	public FactorRelation findByFid1AndFid2AndLabel(long fid1, long fid2,
			String label);

	public List<FactorRelation> findBySchemeId(long schemeId);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "delete from FactorRelation where schemeId=?")
	public void removeBySschemeId(long schemeId);

}
