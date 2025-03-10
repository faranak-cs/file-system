package com.example.filesystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "folder")
public class FolderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "folder_name", nullable = false, unique = true)
    private String folderName;

    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "drive_id", nullable = false)
    private DriveEntity drive;

    @JsonManagedReference
    @OneToMany(mappedBy = "folder", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FileEntity> files;

    @JsonManagedReference
    @OneToMany(mappedBy = "parentFolder", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FolderEntity> subFolders;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "parent_folder")
    private FolderEntity parentFolder;

    public FolderEntity() {
    }

    public FolderEntity(Integer id, String folderName) {
        this.id = id;
        this.folderName = folderName;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFolderName() {
        return this.folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public List<FileEntity> getFiles() {
        return this.files;
    }

    public void setFiles(List<FileEntity> files) {
        this.files = files;
    }

    public List<FolderEntity> getSubFolders() {
        return this.subFolders;
    }

    public void setSubFolders(List<FolderEntity> subFolders) {
        this.subFolders = subFolders;
    }

    public DriveEntity getDrive() {
        return this.drive;
    }

    public void setDrive(DriveEntity drive) {
        this.drive = drive;
    }

    public FolderEntity getParentFolder() {
        return this.parentFolder;
    }

    public void setParentFolder(FolderEntity parentFolder) {
        this.parentFolder = parentFolder;
    }
}