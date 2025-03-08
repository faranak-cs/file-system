package com.example.filesystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.filesystem.service.FileSystemService;

@Tag(name = "Drive Controller", description = "REST API to create or delete a drive in a file system")
@RequiredArgsConstructor
@RequestMapping("/api/v1/drives")
@RestController
public class DriveController {
  private final FileSystemService service;

  @PostMapping
  @Operation(description = "Create a new drive in a file system")
  public String createDrive() {
    return "Drive is created !!!";
  }

  @DeleteMapping
  @Operation(description = "Delete a drive in a file system")
  public String deleteDrive() {
    return "Drive is deleted !!!";
  }
}
