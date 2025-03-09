package com.example.filesystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.filesystem.entity.FolderEntity;

@Repository
public interface FolderRepository extends JpaRepository<FolderEntity, Integer> {
  Optional<FolderEntity> findByFolderName(String folderName);

  void deleteByFolderName(String folderName);
}