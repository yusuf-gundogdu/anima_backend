package com.mycompany.myapp.domain;

import com.mycompany.myapp.domain.enumeration.Platform;
import jakarta.persistence.*;
import java.io.Serializable;

/**
 * A UserAccount.
 */
@Entity
@Table(name = "user_account")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UserAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "timestamp")
    private Integer timestamp;

    @Column(name = "udid")
    private String udid;

    @Enumerated(EnumType.STRING)
    @Column(name = "platform")
    private Platform platform;

    @Column(name = "credit")
    private Integer credit;

    @Column(name = "level")
    private Integer level;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public UserAccount id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTimestamp() {
        return this.timestamp;
    }

    public UserAccount timestamp(Integer timestamp) {
        this.setTimestamp(timestamp);
        return this;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    public String getUdid() {
        return this.udid;
    }

    public UserAccount udid(String udid) {
        this.setUdid(udid);
        return this;
    }

    public void setUdid(String udid) {
        this.udid = udid;
    }

    public Platform getPlatform() {
        return this.platform;
    }

    public UserAccount platform(Platform platform) {
        this.setPlatform(platform);
        return this;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public Integer getCredit() {
        return this.credit;
    }

    public UserAccount credit(Integer credit) {
        this.setCredit(credit);
        return this;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Integer getLevel() {
        return this.level;
    }

    public UserAccount level(Integer level) {
        this.setLevel(level);
        return this;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserAccount)) {
            return false;
        }
        return getId() != null && getId().equals(((UserAccount) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserAccount{" +
            "id=" + getId() +
            ", timestamp=" + getTimestamp() +
            ", udid='" + getUdid() + "'" +
            ", platform='" + getPlatform() + "'" +
            ", credit=" + getCredit() +
            ", level=" + getLevel() +
            "}";
    }
}
