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

@Tag(name = "Folder Controller", description = "REST API to create, delete or move a folder in a file system")
@RequiredArgsConstructor
@RequestMapping("/api/v1/folders")
@RestController
public class FolderController {
  private final FileSystemService service;

  @PostMapping
  @Operation(description = "Create a new folder in a drive in a file system")
  public String createFolder() {
    return "Folder is created !!!";
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
