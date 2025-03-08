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
@Table(name = "folder")
public class FolderEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "folder_name")
  private String folderName;

  @Column(name = "type")
  private String type;

  @Column(name = "path")
  private String path;

  @Column(name = "drive_id")
  private Integer driverId;
}
