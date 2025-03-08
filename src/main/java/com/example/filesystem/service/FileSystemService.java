package com.example.filesystem.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import com.example.filesystem.repository.DriveRepository;
import com.example.filesystem.repository.FileRepository;
import com.example.filesystem.repository.FolderRepository;

@RequiredArgsConstructor
@Service
public class FileSystemService {
  private final DriveRepository driveRepository;

  private final FolderRepository folderRepository;

  private final FileRepository fileRepository;
}