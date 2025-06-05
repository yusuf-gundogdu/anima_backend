package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.UserAccount;
import com.mycompany.myapp.domain.enumeration.Platform;
import com.mycompany.myapp.repository.UserAccountRepository;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.UserAccount}.
 */
@RestController
@RequestMapping("/api/user-accounts")
@Transactional
public class UserAccountResource {

    private final Logger log = LoggerFactory.getLogger(UserAccountResource.class);

    private static final String ENTITY_NAME = "userAccount";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UserAccountRepository userAccountRepository;

    public UserAccountResource(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @PostMapping
    public ResponseEntity<UserAccount> createUserAccount(@RequestBody UserAccount userAccount) throws URISyntaxException {
        log.debug("REST request to save UserAccount : {}", userAccount);
        if (userAccount.getId() != null) {
            throw new BadRequestAlertException("A new userAccount cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserAccount result = userAccountRepository.save(userAccount);
        return ResponseEntity.created(new URI("/api/user-accounts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserAccount> updateUserAccount(@PathVariable Long id, @RequestBody UserAccount userAccount)
        throws URISyntaxException {
        log.debug("REST request to update UserAccount : {}, {}", id, userAccount);
        if (userAccount.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, userAccount.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }
        if (!userAccountRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        UserAccount result = userAccountRepository.save(userAccount);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<UserAccount> partialUpdateUserAccount(@PathVariable Long id, @RequestBody UserAccount userAccount)
        throws URISyntaxException {
        log.debug("REST request to partial update UserAccount : {}, {}", id, userAccount);
        if (userAccount.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, userAccount.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }
        if (!userAccountRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<UserAccount> result = userAccountRepository
            .findById(userAccount.getId())
            .map(existing -> {
                if (userAccount.getTimestamp() != null) existing.setTimestamp(userAccount.getTimestamp());
                if (userAccount.getUdid() != null) existing.setUdid(userAccount.getUdid());
                if (userAccount.getPlatform() != null) existing.setPlatform(userAccount.getPlatform());
                if (userAccount.getCredit() != null) existing.setCredit(userAccount.getCredit());
                if (userAccount.getLevel() != null) existing.setLevel(userAccount.getLevel());
                return existing;
            })
            .map(userAccountRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, userAccount.getId().toString())
        );
    }

    @GetMapping
    public List<UserAccount> getAllUserAccounts() {
        log.debug("REST request to get all UserAccounts");
        return userAccountRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserAccount> getUserAccount(@PathVariable Long id) {
        log.debug("REST request to get UserAccount : {}", id);
        Optional<UserAccount> userAccount = userAccountRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(userAccount);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserAccount(@PathVariable Long id) {
        log.debug("REST request to delete UserAccount : {}", id);
        userAccountRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }

    @GetMapping("/udid/{udid}")
    public ResponseEntity<UserAccount> getUserAccountByUdid(@PathVariable String udid) {
        log.debug("REST request to get UserAccount by udid : {}", udid);
        Optional<UserAccount> userAccount = userAccountRepository.findByUdid(udid);

        return ResponseUtil.wrapOrNotFound(userAccount);
    }

    //.requestMatchers(mvc.pattern("/api/user-accounts/account/**")).permitAll()
    @GetMapping("/account/{platform}/{udid}")
    public ResponseEntity<UserAccount> getUserAccountByPlatformAndUdid(@PathVariable Platform platform, @PathVariable String udid) {
        log.debug("REST request to get UserAccount by platform: {} and udid: {}", platform, udid);

        // 1. Önce veritabanında sorgula
        Optional<UserAccount> existingUserAccount = userAccountRepository.findByPlatformAndUdid(platform, udid);

        // 2. Eğer kayıt varsa, direkt dön
        if (existingUserAccount.isPresent()) {
            return ResponseUtil.wrapOrNotFound(existingUserAccount);
        }

        // 3. Kayıt yoksa, yeni bir tane oluştur
        UserAccount newUserAccount = new UserAccount();
        newUserAccount.setPlatform(platform);
        newUserAccount.setUdid(udid);
        newUserAccount.setLevel(0); // Varsayılan level: 0
        newUserAccount.setCredit(100); // Varsayılan credit: 100
        newUserAccount.setTimestamp((int) Instant.now().getEpochSecond());
        UserAccount savedUserAccount = userAccountRepository.save(newUserAccount);

        // 5. Oluşturulan kaydı dön
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUserAccount); // 201 Created kodu ile dön
    }
}
