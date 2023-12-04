package com.qa.demo.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.demo.domain.Tarantula;

@Repository
public interface TarantulaRepo extends JpaRepository<Tarantula, Long> {

	// Conventionally indexes are used even for single para methods
	// nativeQuery (true = non-JPQL)
	@Query(value = "SELECT * FROM Tarantula WHERE common_name =?1", nativeQuery = true)
	List<Tarantula> findByName(String name);

	// With Pagination
	@Query(value = "SELECT * FROM Tarantula ORDER BY id \n-- #pageable\n", countQuery = "SELECT count(*) FROM Tarantula", nativeQuery = true)
	Page<Tarantula> findAllTarantulaNativeWithPagination(Pageable pageable);

	// JPQL Queries
	@Query(value = "SELECT c FROM Tarantula c")
	List<Tarantula> findAllTarantula(Sort sort);

	@Query(value = "SELECT c FROM Tarantula c ORDER BY id")
	Page<Tarantula> findAllTarantulaWithPagination(Pageable pageable);

}
