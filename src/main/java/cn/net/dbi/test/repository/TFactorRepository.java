package cn.net.dbi.test.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import cn.net.dbi.test.model.Factor;

public interface TFactorRepository extends
		PagingAndSortingRepository<Factor, Long> {

}
