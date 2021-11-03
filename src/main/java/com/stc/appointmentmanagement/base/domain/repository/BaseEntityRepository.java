package com.stc.appointmentmanagement.base.domain.repository;


import com.stc.appointmentmanagement.base.domain.entities.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseEntityRepository<T extends BaseEntity<ID>, ID extends Number> extends JpaRepository<T, ID> {
}
