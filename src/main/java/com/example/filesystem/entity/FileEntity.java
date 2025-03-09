package com.example.filesystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Setter
@Getter
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

  @JsonBackReference
  @ManyToOne(optional = false)
  @JoinColumn(name = "drive_id", nullable = false)
  private DriveEntity drive;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "folder_id")
  private FolderEntity folder;
}