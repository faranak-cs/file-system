package com.example.filesystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.filesystem.entity.DriveEntity;

@Repository
public interface DriveRepository extends JpaRepository<DriveEntity, Integer> {

  Optional<DriveEntity> findByDriveName(String driveName);

  void deleteByDriveName(String driveName);
}