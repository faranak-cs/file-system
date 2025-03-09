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
import com.example.filesystem.entity.FileEntity;
import com.example.filesystem.entity.FolderEntity;
import com.example.filesystem.repository.DriveRepository;
import com.example.filesystem.repository.FileRepository;
import com.example.filesystem.repository.FolderRepository;

@Tag(name = "File Controller", description = "REST API to create, delete, move or write in a file in a file system")
@RequiredArgsConstructor
@RequestMapping("/api/v1/files")
@RestController
public class FileController {
  private final FileRepository fileRepository;

  private final FolderRepository folderRepository;

  private final DriveRepository driveRepository;

  @GetMapping
  @Operation(description = "Get all files in a file system")
  public ResponseEntity<List<FileEntity>> getFiles() {
    return ResponseEntity
        .status(200)
        .body(fileRepository.findAll());
  }

  @PostMapping
  @Operation(description = "Create a new file in a folder or a drive in a file system")
  public ResponseEntity<FileEntity> createFile(
      @RequestParam String driveName,
      @RequestParam(required = false) String folderName,
      @RequestParam String fileName) {
    Optional<DriveEntity> drive = driveRepository.findByDriveName(driveName);
    if (drive.isPresent()) {
      FileEntity file = new FileEntity();
      file.setFileName(fileName);
      file.setDrive(drive.get());
      if (folderName != null) {
        Optional<FolderEntity> folder = folderRepository.findByFolderName(folderName);
        folder.ifPresent(file::setFolder);
      }
      FileEntity response = fileRepository.save(file);
      return ResponseEntity
          .status(200)
          .body(response);
    }
    return null;
  }

  @DeleteMapping("/{fileName}")
  @Operation(description = "Delete a file in folder or a drive in a file system")
  public ResponseEntity<String> deleteFile(@PathVariable String fileName) {
    fileRepository.deleteByFileName(fileName);
    return ResponseEntity
        .status(204)
        .body("File is deleted");
  }

  @PutMapping
  @Operation(description = "Move a file from a folder/drive to a folder/drive in a file system")
  public String moveFile() {
    return "File is moved !!!";
  }

  @PostMapping("/write")
  @Operation(description = "Write content in a file in a file system ")
  public String writeFile() {
    return "Content is written to file !!!";
  }
}