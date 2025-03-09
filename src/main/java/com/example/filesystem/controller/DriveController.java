package com.example.filesystem.controller;

import java.util.List;

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
  @Operation(description = "Get all drives in a file system")
  public ResponseEntity<List<DriveEntity>> getDrives() {
    return ResponseEntity
        .status(200)
        .body(repository.findAll());
  }

  @PostMapping
  @Operation(description = "Create a new drive in a file system")
  public ResponseEntity<DriveEntity> createDrive(@RequestParam String driveName) {
    DriveEntity drive = new DriveEntity();
    drive.setDriveName(driveName);
    return ResponseEntity
        .status(200)
        .body(repository.save(drive));
  }

  @DeleteMapping("/{id}")
  @Operation(description = "Delete a drive in a file system")
  public ResponseEntity<String> deleteDrive(@PathVariable Integer id) {
    repository.deleteById(id);
    return ResponseEntity
        .status(204)
        .body("Drive is deleted");
  }
}
