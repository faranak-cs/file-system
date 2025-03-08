package com.example.filesystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "file")
public class FileEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "file_name")
  private String fileName;

  @Column(name = "type")
  private String type;

  @Column(name = "path")
  private String path;

  @Column(name = "drive_id")
  private String driveId;

  @Column(name = "folder_id")
  private String folderId;
}