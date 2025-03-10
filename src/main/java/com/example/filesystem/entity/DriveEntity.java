package com.example.filesystem.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "drive")
public class DriveEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "drive_name", nullable = false, unique = true)
    private String driveName;

    @JsonManagedReference
    @OneToMany(mappedBy = "drive", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FileEntity> files;

    @JsonManagedReference
    @OneToMany(mappedBy = "drive", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FolderEntity> folders;

    public DriveEntity() {
    }

    public DriveEntity(Integer id, String driveName) {
        this.id = id;
        this.driveName = driveName;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDriveName() {
        return this.driveName;
    }

    public void setDriveName(String driveName) {
        this.driveName = driveName;
    }

    public List<FileEntity> getFiles() {
        return this.files;
    }

    public void setFiles(List<FileEntity> files) {
        this.files = files;
    }

    public List<FolderEntity> getFolders() {
        return this.folders;
    }

    public void setFolders(List<FolderEntity> folders) {
        this.folders = folders;
    }
}