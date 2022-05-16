package com.hcaglar.dynamicquery.repository;

import com.hcaglar.dynamicquery.entity.Person;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * @author Hüseyin ÇAĞLAR
 * @version 1.0
 * @since 15.05.2022
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {
    List<Person> findAll(Specification<Person> personSpecification);
}
