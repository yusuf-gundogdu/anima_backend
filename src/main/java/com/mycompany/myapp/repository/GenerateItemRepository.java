package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.GenerateItem;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the GenerateItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GenerateItemRepository extends JpaRepository<GenerateItem, Long> {}
