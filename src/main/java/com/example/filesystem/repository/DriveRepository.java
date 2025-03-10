package com.example.filesystem.repository;

import com.example.filesystem.entity.DriveEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriveRepository extends JpaRepository<DriveEntity, Integer> {

    Optional<DriveEntity> findByDriveName(String driveName);

    void deleteByDriveName(String driveName);
}