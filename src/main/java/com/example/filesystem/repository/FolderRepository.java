package com.example.filesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.filesystem.entity.FolderEntity;

@Repository
public interface FolderRepository extends JpaRepository<FolderEntity, Integer> {
}