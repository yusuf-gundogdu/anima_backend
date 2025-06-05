package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;

/**
 * A ImageAsset.
 */
@Entity
@Table(name = "image_asset")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ImageAsset implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "file_size")
    private Long fileSize;

    @JsonIgnoreProperties(value = { "image", "generateList" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "image")
    private GenerateItem generateItem;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ImageAsset id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return this.fileName;
    }

    public ImageAsset fileName(String fileName) {
        this.setFileName(fileName);
        return this;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public ImageAsset filePath(String filePath) {
        this.setFilePath(filePath);
        return this;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Long getFileSize() {
        return this.fileSize;
    }

    public ImageAsset fileSize(Long fileSize) {
        this.setFileSize(fileSize);
        return this;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public GenerateItem getGenerateItem() {
        return this.generateItem;
    }

    public void setGenerateItem(GenerateItem generateItem) {
        if (this.generateItem != null) {
            this.generateItem.setImage(null);
        }
        if (generateItem != null) {
            generateItem.setImage(this);
        }
        this.generateItem = generateItem;
    }

    public ImageAsset generateItem(GenerateItem generateItem) {
        this.setGenerateItem(generateItem);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ImageAsset)) {
            return false;
        }
        return getId() != null && getId().equals(((ImageAsset) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ImageAsset{" +
            "id=" + getId() +
            ", fileName='" + getFileName() + "'" +
            ", filePath='" + getFilePath() + "'" +
            ", fileSize=" + getFileSize() +
            "}";
    }
}
