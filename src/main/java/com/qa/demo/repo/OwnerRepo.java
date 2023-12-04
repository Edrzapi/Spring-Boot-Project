package com.qa.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.demo.domain.Owner;

@Repository
public interface OwnerRepo extends JpaRepository<Owner, Long> {
}
