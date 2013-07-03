package cn.net.dbi.test.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.net.dbi.test.model.Scheme;

public interface SchemeRepository extends
		PagingAndSortingRepository<Scheme, Long> {
	@Query(nativeQuery = true, value = "delete from Scheme where id=?")
	public void removeBySschemeId(long schemeId);
}
