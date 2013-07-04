package cn.net.dbi.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import cn.net.dbi.test.model.FactorAttr;

public interface FactorAttrRepository extends
		PagingAndSortingRepository<FactorAttr, Long> {

	public List<FactorAttr> findBySchemeId(long schemeId);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "delete from FactorAttr where schemeId=?")
	public void removeBySchemeId(long schemeId);
}
