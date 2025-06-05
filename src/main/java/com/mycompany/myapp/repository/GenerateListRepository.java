package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.GenerateList;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the GenerateList entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GenerateListRepository extends JpaRepository<GenerateList, Long> {}
