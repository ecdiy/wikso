package cn.net.dbi.test.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import cn.net.dbi.test.model.FactorRelation;

public interface FactorRelationRepository extends
		PagingAndSortingRepository<FactorRelation, Long> {

	public FactorRelation findByFid1AndFid2AndLabel(long fid1, long fid2,
			String label);

}
