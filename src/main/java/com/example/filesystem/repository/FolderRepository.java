package com.example.filesystem.repository;

import com.example.filesystem.entity.FolderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FolderRepository extends JpaRepository<FolderEntity, Integer> {

    Optional<FolderEntity> findByFolderName(String folderName);

    void deleteByFolderName(String folderName);
}