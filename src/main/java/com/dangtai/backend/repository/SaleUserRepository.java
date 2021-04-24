package com.dangtai.backend.repository;

import com.dangtai.backend.entity.SaleUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleUserRepository extends JpaRepository<SaleUserEntity, Long> {
}
