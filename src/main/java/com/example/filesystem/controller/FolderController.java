package com.example.filesystem.controller;

import java.util.List;
import java.util.Optional;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
  private final FolderRepository repository;

  private final DriveRepository driveRepository;

  @GetMapping
  @Operation(description = "Get all folders in a file system")
  public ResponseEntity<List<FolderEntity>> getFolders() {
    return ResponseEntity
        .status(200)
        .body(repository.findAll());
  }

  @PostMapping
  @Operation(description = "Create a new folder in a drive in a file system")
  public ResponseEntity<FolderEntity> createFolder(@RequestParam(value = "driveName") final String driveName,
      @RequestParam(value = "parentFolderName", required = false) final String parentFolderName,
      @RequestParam(value = "folderName") final String folderName
  ) {
    Optional<DriveEntity> drive = driveRepository.findByDriveName(driveName);
    FolderEntity folder = new FolderEntity();
    folder.setFolderName(folderName);
    folder.setDrive(drive.get());

    return ResponseEntity
        .status(200)
        .body(repository.save(folder));
  }

  @DeleteMapping
  @Operation(description = "Delete a folder in a file system")
  public String deleteFolder() {
    return "Folder is deleted !!!";
  }

  @PutMapping
  @Operation(description = "Move a folder to another folder or drive in a file system")
  public String moveFolder() {
    return "Folder is moved !!!";
  }
}
