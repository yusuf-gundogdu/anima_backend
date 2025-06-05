package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;

/**
 * A GenerateItem.
 */
@Entity
@Table(name = "generate_item")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class GenerateItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "credit")
    private Integer credit;

    @Column(name = "level")
    private Integer level;

    @JsonIgnoreProperties(value = { "generateItem" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private ImageAsset image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "contents" }, allowSetters = true)
    private GenerateList generateList;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public GenerateItem id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public GenerateItem name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCredit() {
        return this.credit;
    }

    public GenerateItem credit(Integer credit) {
        this.setCredit(credit);
        return this;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Integer getLevel() {
        return this.level;
    }

    public GenerateItem level(Integer level) {
        this.setLevel(level);
        return this;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public ImageAsset getImage() {
        return this.image;
    }

    public void setImage(ImageAsset imageAsset) {
        this.image = imageAsset;
    }

    public GenerateItem image(ImageAsset imageAsset) {
        this.setImage(imageAsset);
        return this;
    }

    public GenerateList getGenerateList() {
        return this.generateList;
    }

    public void setGenerateList(GenerateList generateList) {
        this.generateList = generateList;
    }

    public GenerateItem generateList(GenerateList generateList) {
        this.setGenerateList(generateList);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GenerateItem)) {
            return false;
        }
        return getId() != null && getId().equals(((GenerateItem) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GenerateItem{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", credit=" + getCredit() +
            ", level=" + getLevel() +
            "}";
    }
}
