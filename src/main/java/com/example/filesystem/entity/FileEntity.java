package com.example.filesystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Entity
@Table(name = "file")
public class FileEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "file_name", nullable = false)
  private String fileName;

  @Column(name = "content")
  private String content;

  @Column(name = "type")
  private String type;

  @Column(name = "path")
  private String path;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_id", nullable = false)
  private FolderEntity parentFolder;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "drive_id", nullable = false)
  private DriveEntity drive;
}