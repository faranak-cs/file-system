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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.filesystem.entity.DriveEntity;
import com.example.filesystem.entity.FolderEntity;
import com.example.filesystem.repository.DriveRepository;
import com.example.filesystem.repository.FolderRepository;

@Tag(name = "Folder Controller", description = "REST API to create, delete or move a folder in a file system")
@RequiredArgsConstructor
@RequestMapping("/api/v1/folders")
@RestController
public class FolderController {
  private final FolderRepository folderRepository;

  private final DriveRepository driveRepository;

  @GetMapping
  @Operation(summary = "Get all folders with associated files/folders in a file system",
      description = "Get all folders with associated files/folders in a file system")
  public ResponseEntity<List<FolderEntity>> getFolders() {
    return ResponseEntity.ok(folderRepository.findAll());
  }

  @PostMapping
  @Operation(summary = "Create a new folder in a drive in a file system",
      description = "Create a new folder in a drive in a file system. MUST provide a drive name")
  public ResponseEntity<String> createFolderInDrive(
      @RequestParam final String driveName,
      @RequestParam final String folderName
  ) {

    if (driveName == null || driveName.isBlank() || folderName.isBlank()) {
      return ResponseEntity
          .badRequest()
          .body("Drive/Folder name(s) cannot be null, empty or blank.");
    }

    Optional<DriveEntity> drive = driveRepository.findByDriveName(driveName);
    if (drive.isEmpty()) {
      return ResponseEntity
          .badRequest()
          .body("No such drive exists.");
    }

    FolderEntity folder = new FolderEntity();
    folder.setFolderName(folderName);
    folder.setDrive(drive.get());
    FolderEntity saved = folderRepository.save(folder);
    return ResponseEntity.ok("Folder is created in a drive. ID: " + saved.getId());
  }

  @PostMapping("/sub-folders")
  @Operation(summary = "Create a new folder in a drive in a file system",
      description = "Create a new folder in a parent folder in a drive in a file system. "
          + "MUST provide a drive name and a parent folder name")
  public ResponseEntity<String> createFolderInFolder(
      @RequestParam final String driveName,
      @RequestParam final String parentFolderName,
      @RequestParam final String folderName
  ) {

    if (driveName == null || driveName.isBlank() || folderName.isBlank() || parentFolderName.isBlank()) {
      return ResponseEntity
          .badRequest()
          .body("Drive/Folder name(s) cannot be null, empty or blank.");
    }

    Optional<DriveEntity> drive = driveRepository.findByDriveName(driveName);
    if (drive.isEmpty()) {
      return ResponseEntity
          .badRequest()
          .body("No such drive exists.");
    }

    Optional<FolderEntity> parentFolder = folderRepository.findByFolderName(parentFolderName);
    if (parentFolder.isEmpty()) {
      return ResponseEntity
          .badRequest()
          .body("No such parent drive exists");
    }

    FolderEntity folder = new FolderEntity();
    folder.setFolderName(folderName);
    folder.setDrive(drive.get());
    folder.setParentFolder(parentFolder.get());
    FolderEntity saved = folderRepository.save(folder);
    return ResponseEntity.ok("Folder is created in a parent folder in a drive. ID: " + saved.getId());
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete a folder in a file system",
      description = "Delete a folder with associated files/folders in a file system using an id")
  public ResponseEntity<String> deleteFolder(@PathVariable Integer id) {
    Optional<FolderEntity> folder = folderRepository.findById(id);
    if (folder.isEmpty()) {
      return ResponseEntity
          .badRequest()
          .body("No folder exists with provided id.");
    }
    folderRepository.deleteById(id);
    return ResponseEntity
        .ok("Folder is deleted. ID: " + id);
  }

  @PutMapping
  @Operation(description = "Move a folder to another folder or drive in a file system")
  public String moveFolder(@RequestParam final String driveName) {
    return "Folder is moved !!!";
  }
}