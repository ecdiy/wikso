package cn.net.dbi.test.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import cn.net.dbi.test.model.Scheme;

public interface SchemeRepository extends
		PagingAndSortingRepository<Scheme, Long> {

}
