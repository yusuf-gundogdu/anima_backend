package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ImageAsset;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ImageAsset entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ImageAssetRepository extends JpaRepository<ImageAsset, Long> {}
