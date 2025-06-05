package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A GenerateList.
 */
@Entity
@Table(name = "generate_list")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class GenerateList implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "generateList")
    @JsonIgnoreProperties(value = { "image", "generateList" }, allowSetters = true)
    private Set<GenerateItem> contents = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public GenerateList id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public GenerateList title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<GenerateItem> getContents() {
        return this.contents;
    }

    public void setContents(Set<GenerateItem> generateItems) {
        if (this.contents != null) {
            this.contents.forEach(i -> i.setGenerateList(null));
        }
        if (generateItems != null) {
            generateItems.forEach(i -> i.setGenerateList(this));
        }
        this.contents = generateItems;
    }

    public GenerateList contents(Set<GenerateItem> generateItems) {
        this.setContents(generateItems);
        return this;
    }

    public GenerateList addContent(GenerateItem generateItem) {
        this.contents.add(generateItem);
        generateItem.setGenerateList(this);
        return this;
    }

    public GenerateList removeContent(GenerateItem generateItem) {
        this.contents.remove(generateItem);
        generateItem.setGenerateList(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GenerateList)) {
            return false;
        }
        return getId() != null && getId().equals(((GenerateList) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GenerateList{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            "}";
    }
}
