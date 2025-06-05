package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.UserAccount;
import com.mycompany.myapp.domain.enumeration.Platform;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the UserAccount entity.
 */
@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    Optional<UserAccount> findByUdid(String udid);
    Optional<UserAccount> findByPlatformAndUdid(Platform platform, String udid);
}
