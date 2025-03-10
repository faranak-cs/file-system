package com.example.filesystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "file")
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "file_name", nullable = false, unique = true)
    private String fileName;

    @Column(name = "content")
    private String content;

    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "drive_id", nullable = false)
    private DriveEntity drive;

    @JsonBackReference
    @ManyToOne(optional = true)
    @JoinColumn(name = "folder_id", nullable = true)
    private FolderEntity folder;

    public FileEntity() {
    }

    public FileEntity(Integer id, String fileName, String content) {
        this.id = id;
        this.fileName = fileName;
        this.content = content;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public DriveEntity getDrive() {
        return this.drive;
    }

    public void setDrive(DriveEntity drive) {
        this.drive = drive;
    }

    public FolderEntity getFolder() {
        return this.folder;
    }

    public void setFolder(FolderEntity folder) {
        this.folder = folder;
    }
}