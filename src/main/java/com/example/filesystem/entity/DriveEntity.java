package com.example.filesystem.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Entity
@Table(name = "drive")
public class DriveEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "drive_name")
  private String driveName;

  @Column(name = "type")
  private String type;

  @Column(name = "path")
  private String path;

  @OneToMany(mappedBy = "drive", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<FileEntity> files;

  @OneToMany(mappedBy = "drive", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<FolderEntity> folders;
}