package com.example.filesystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.filesystem.service.FileSystemService;

@Tag(name = "File Controller", description = "REST API to create, delete, move or write in a file in a file system")
@RequiredArgsConstructor
@RequestMapping("/api/v1/files")
@RestController
public class FileController {
  private final FileSystemService service;

  @PostMapping
  @Operation(description = "Create a new file in a folder or a drive in a file system")
  public String createFile() {
    return "File is created !!!";
  }

  @DeleteMapping
  @Operation(description = "Delete a file in folder or a drive in a file system")
  public String deleteFile() {
    return "File is deleted !!!";
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