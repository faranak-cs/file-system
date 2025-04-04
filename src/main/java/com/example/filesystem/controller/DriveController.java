package com.example.filesystem.controller;

import com.example.filesystem.entity.DriveEntity;
import com.example.filesystem.repository.DriveRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Drive Controller", description = "REST API to create or delete a drive in a file system")
@RequestMapping("/api/v1/drives")
@RestController
public class DriveController {

    private final DriveRepository driveRepository;

    public DriveController(DriveRepository driveRepository) {
        this.driveRepository = driveRepository;
    }

    @GetMapping
    @Operation(summary = "Get all drives with associated files/folders in a file system",
            description = "Get all drives with associated files/folders in a file system")
    public ResponseEntity<List<DriveEntity>> getDrives() {
        return ResponseEntity.ok(driveRepository.findAll());
    }

    @PostMapping
    @Operation(summary = "Create a new drive in a file system",
            description = "Create a new drive in a file system. Drive name must be unique.")
    public ResponseEntity<String> createDrive(@RequestParam final String driveName) {
        if (driveName == null || driveName.isBlank()) {
            return ResponseEntity
                    .badRequest()
                    .body("Drive name cannot be null, empty or blank.");
        }
        if (driveRepository
                .findByDriveName(driveName)
                .isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body("Drive already exists.");
        }
        DriveEntity drive = new DriveEntity();
        drive.setDriveName(driveName);
        DriveEntity saved = driveRepository.save(drive);
        return ResponseEntity.ok("Drive is created. ID: " + saved.getId());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a drive in a file system",
            description = "Delete a drive with associated files/folders in a file system using an id")
    public ResponseEntity<String> deleteDrive(@PathVariable final Integer id) {
        Optional<DriveEntity> drive = driveRepository.findById(id);
        if (drive.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body("No such drive exists.");
        }
        driveRepository.deleteById(id);
        return ResponseEntity
                .ok("Drive is deleted. ID: " + id);
    }
}