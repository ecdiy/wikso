package cn.net.dbi.test.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import cn.net.dbi.test.model.Factor;

public interface TFactorRepository extends
		PagingAndSortingRepository<Factor, Long> {

	public List<Factor> findBySchemeId(long schemeId);
}
