package com.example.filesystem.controller;

import java.util.List;
import java.util.Optional;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.filesystem.entity.DriveEntity;
import com.example.filesystem.repository.DriveRepository;

@Tag(name = "Drive Controller", description = "REST API to create or delete a drive in a file system")
@RequiredArgsConstructor
@RequestMapping("/api/v1/drives")
@RestController
public class DriveController {

  private final DriveRepository repository;

  @GetMapping
  @Operation(summary = "Get all drives with associated files/folders in a file system",
      description = "Get all drives with associated files/folders in a file system")
  public ResponseEntity<List<DriveEntity>> getDrives() {
    return ResponseEntity.ok(repository.findAll());
  }

  @PostMapping
  @Operation(summary = "Create a new drive in a file system",
      description = "Create a new drive in a file system. Drive name must be unique.")
  public ResponseEntity<String> createDrive(@RequestParam String driveName) {
    if (driveName == null || driveName.isBlank()) {
      return ResponseEntity
          .badRequest()
          .body("Drive name cannot be null, empty or blank.");
    }
    if (repository
        .findByDriveName(driveName)
        .isPresent()) {
      return ResponseEntity
          .badRequest()
          .body("Drive name already exists.");
    }
    DriveEntity drive = new DriveEntity();
    drive.setDriveName(driveName);
    DriveEntity saved = repository.save(drive);
    return ResponseEntity.ok("Drive is created. ID: " + saved.getId());
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete a drive in a file system",
      description = "Delete a drive with associated files/folders in a file system using an id")
  public ResponseEntity<String> deleteDrive(@PathVariable Integer id) {
    Optional<DriveEntity> drive = repository.findById(id);
    if (drive.isEmpty()) {
      return ResponseEntity
          .badRequest()
          .body("No drive exists with provided id.");
    }
    repository.deleteById(id);
    return ResponseEntity
        .ok("Drive is deleted. ID: " + id);
  }
}