package com.example.filesystem.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Setter
@Getter
@Entity
@Table(name = "folder")
public class FolderEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "folder_name", nullable = false)
  private String folderName;

  @JsonBackReference
  @ManyToOne(optional = false)
  @JoinColumn(name = "drive_id", nullable = false)
  private DriveEntity drive;

  @JsonManagedReference
  @OneToMany(mappedBy = "folder", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
  private List<FileEntity> files;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "parent_folder")
  private FolderEntity parentFolder;

  @JsonManagedReference
  @OneToMany(mappedBy = "parentFolder")
  private List<FolderEntity> subFolders;
}