package com.example.filesystem.controller;

import com.example.filesystem.entity.DriveEntity;
import com.example.filesystem.entity.FileEntity;
import com.example.filesystem.entity.FolderEntity;
import com.example.filesystem.repository.DriveRepository;
import com.example.filesystem.repository.FileRepository;
import com.example.filesystem.repository.FolderRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "File Controller", description = "REST API to create, delete, move or write in a file in a file system")
@RequestMapping("/api/v1/files")
@RestController
public class FileController {
    private final FileRepository fileRepository;

    private final FolderRepository folderRepository;

    private final DriveRepository driveRepository;

    public FileController(FileRepository fileRepository,
                          FolderRepository folderRepository,
                          DriveRepository driveRepository) {
        this.fileRepository = fileRepository;
        this.folderRepository = folderRepository;
        this.driveRepository = driveRepository;
    }

    @GetMapping
    @Operation(summary = "Get all files in a file system",
            description = "Get all files in a file system")
    public ResponseEntity<List<FileEntity>> getFiles() {
        return ResponseEntity.ok(fileRepository.findAll());
    }

    @PostMapping("/drives")
    @Operation(summary = "Create a new file in a drive in a file system",
            description = "Create a new file in a drive in a file system using file name and drive name")
    public ResponseEntity<String> createFileInDrive(@RequestParam final String driveName,
                                                    @RequestParam final String fileName) {
        Optional<DriveEntity> drive = driveRepository.findByDriveName(driveName);
        if (drive.isEmpty()) {
            return ResponseEntity.badRequest().body("No such drive exists.");
        }
        FileEntity file = new FileEntity();
        file.setFileName(fileName);
        file.setDrive(drive.get());
        FileEntity saved = fileRepository.save(file);
        return ResponseEntity.ok("File is created in a drive. ID: " + saved.getId());
    }

    @PostMapping("/folders")
    @Operation(summary = "Create a new file in a folder in a file system",
            description = "Create a new file in a folder in a drive in a file system. " +
                    "MUST provide a drive name and a folder name")
    public ResponseEntity<String> createFileInFolder(@RequestParam final String driveName,
                                                     @RequestParam final String folderName,
                                                     @RequestParam final String fileName) {
        if (driveName == null || driveName.isBlank() || folderName.isBlank() || fileName.isBlank()) {
            return ResponseEntity
                    .badRequest()
                    .body("Drive/Folder/File name(s) cannot be null, empty or blank.");
        }
        Optional<DriveEntity> drive = driveRepository.findByDriveName(driveName);
        if (drive.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body("No such drive exists.");
        }
        Optional<FolderEntity> folder = folderRepository.findByFolderName(folderName);
        if (folder.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body("No such folder exists.");
        }
        FileEntity file = new FileEntity();
        file.setFileName(fileName);
        file.setFolder(folder.get());
        file.setDrive(drive.get());
        FileEntity saved = fileRepository.save(file);
        return ResponseEntity.ok("File is created in a folder in a drive. ID: " + saved.getId());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a file in folder or a drive in a file system",
            description = "Delete a file in folder or a drive in a file system using an id")
    public ResponseEntity<String> deleteFile(@PathVariable final Integer id) {
        Optional<FileEntity> file = fileRepository.findById(id);
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("No such file exists.");
        }
        fileRepository.deleteById(id);
        return ResponseEntity.ok("File is deleted. ID: " + id);
    }

    @PutMapping("/drives")
    @Operation(summary = "Move a file from a drive to another drive in a file system",
            description = "Move a file from a drive to another drive in a file system " +
                    "using file name and drive name")
    public ResponseEntity<String> moveFile(@RequestParam final String fileName,
                                           @RequestParam final String driveName) {
        Optional<FileEntity> file = fileRepository.findByFileName(fileName);
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("No such file exists.");
        }
        Optional<DriveEntity> drive = driveRepository.findByDriveName(driveName);
        if (drive.isEmpty()) {
            return ResponseEntity.badRequest().body("No such drive exists.");
        }
        file.get().setDrive(drive.get());
        FileEntity saved = fileRepository.save(file.get());

        return ResponseEntity.ok("File is moved to drive: " + saved.getDrive().getDriveName());
    }

    @PostMapping()
    @Operation(summary = "Write content in a file in a file system",
            description = "Write content in a file in a file system using a file name")
    public ResponseEntity<String> writeFile(@RequestParam final String fileName,
                                            @RequestParam final String content) {
        Optional<FileEntity> file = fileRepository.findByFileName(fileName);
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("No such file exists");
        }
        if (content == null || content.isBlank()) {
            return ResponseEntity.badRequest().body("Content cannot be null, empty or blank.");
        }
        file.get().setContent(content);
        FileEntity saved = fileRepository.save(file.get());
        return ResponseEntity.ok("Content is written to file: " + saved.getFileName());
    }
}