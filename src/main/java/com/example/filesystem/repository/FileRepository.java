package com.example.filesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.filesystem.entity.FileEntity;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, Integer> {
  void deleteByFileName(String fileName);
}