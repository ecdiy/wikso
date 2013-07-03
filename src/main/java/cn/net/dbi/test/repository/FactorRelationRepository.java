package cn.net.dbi.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.net.dbi.test.model.FactorRelation;

public interface FactorRelationRepository extends
		PagingAndSortingRepository<FactorRelation, Long> {

	public FactorRelation findByFid1AndFid2AndLabel(long fid1, long fid2,
			String label);

	public List<FactorRelation> findBySchemeId(long schemeId);

	@Query(nativeQuery = true, value = "delete from FactorRelation where schemeId=?")
	public void removeBySschemeId(long schemeId);

}
