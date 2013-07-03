package cn.net.dbi.test.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import cn.net.dbi.test.model.FactorAttr;

public interface FactorAttrRepository extends
		PagingAndSortingRepository<FactorAttr, Long> {

	public List<FactorAttr> findBySchemeId(long schemeId);
}
