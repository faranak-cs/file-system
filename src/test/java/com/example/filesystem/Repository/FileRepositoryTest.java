package com.example.filesystem.Repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.filesystem.entity.DriveEntity;
import com.example.filesystem.repository.DriveRepository;
import com.example.filesystem.repository.FileRepository;
import com.example.filesystem.repository.FolderRepository;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class FileRepositoryTest {

  @Autowired
  private FileRepository fileRepository;

  @Autowired
  private FolderRepository folderRepository;

  @Autowired
  private DriveRepository driveRepository;

  @Test
  void contextLoads() {
    assertThat(fileRepository).isNotNull();
    assertThat(folderRepository).isNotNull();
    assertThat(driveRepository).isNotNull();
  }

  @Test
  void testCreateDrive() {
    //given
    DriveEntity drive = DriveEntity
        .builder()
        .driveName("C")
        .build();

    //when

    //then
  }

  @Test
  void testCreateFolder_givenThereExistADrive() {
    //given

    //when

    //then
  }

  @Test
  void testCreateFolder_givenThereExistAFolder() {
    //given

    //when

    //then
  }

  @Test
  void testCreateFile_givenThereExistADrive() {
    //given

    //when

    //then
  }

  @Test
  void testCreateFile_givenThereExistAFolder() {
    //given

    //when

    //then
  }
}
