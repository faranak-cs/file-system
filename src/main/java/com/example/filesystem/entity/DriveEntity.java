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
import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Setter
@Getter
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
}