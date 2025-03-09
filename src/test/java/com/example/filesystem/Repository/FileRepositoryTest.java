package com.example.filesystem.Repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.filesystem.entity.DriveEntity;
import com.example.filesystem.entity.FolderEntity;
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
  void testCreateDrive_givenThereExistNoDrive_pass() {
    //given
    DriveEntity drive = DriveEntity
        .builder()
        .driveName("C")
        .build();
    driveRepository.save(drive);

    //when
    Optional<DriveEntity> actual = driveRepository.findById(drive.getId());

    //then
    assertThat(actual.isPresent()).isEqualTo(true);
    assertThat(actual
        .get()
        .getDriveName()).isEqualTo("C");
  }

  @Test
  @Disabled("this test is currently passing, in fact this should fail")
  void testCreateDrive_givenThereExistADriveWithSameName_throwsException() {
    //given
    DriveEntity drive1 = DriveEntity
        .builder()
        .driveName("C")
        .build();
    driveRepository.save(drive1);

    DriveEntity drive2 = DriveEntity
        .builder()
        .driveName("C")
        .build();
    driveRepository.save(drive2);

    //when
    Optional<DriveEntity> actual = driveRepository.findById(drive1.getId());

    //then
    assertThat(actual.isPresent()).isEqualTo(true);
    assertThat(actual
        .get()
        .getDriveName()).isEqualTo("C");
  }

  @Test
  void testCreateFolder_givenThereExistADrive_pass() {
    //given
    DriveEntity drive = DriveEntity
        .builder()
        .driveName("C")
        .build();
    driveRepository.save(drive);

    FolderEntity folder = FolderEntity
        .builder()
        .folderName("Test")
        .drive(drive)
        .build();
    folderRepository.save(folder);

    //when
    Optional<FolderEntity> actual = folderRepository.findById(folder.getId());

    //then
    assertThat(actual.isPresent()).isEqualTo(true);
    assertThat(actual
        .get()
        .getFolderName()).isEqualTo("Test");
  }

  @Test
  void testCreateFolder_givenThereExistNoDrive_throwsException() {
    //given
    FolderEntity folder = FolderEntity
        .builder()
        .folderName("Test")
        .build();

    //then
    assertThatThrownBy(() -> folderRepository.save(folder))
        .isInstanceOf(DataIntegrityViolationException.class)
        .hasMessageContaining("not-null property references a null or transient value");
  }

  @Test
  void testCreateFolder_givenThereExistAFolder_pass() {
    //given
    DriveEntity drive = DriveEntity
        .builder()
        .driveName("C")
        .build();
    driveRepository.save(drive);

    FolderEntity parentFolder = FolderEntity
        .builder()
        .folderName("Parent")
        .drive(drive)
        .build();
    folderRepository.save(parentFolder);

    FolderEntity childFolder = FolderEntity
        .builder()
        .folderName("Child")
        .drive(drive)
        .parentFolder(parentFolder)
        .build();
    folderRepository.save(childFolder);

    //when
    Optional<FolderEntity> actualFolder = folderRepository.findById(childFolder.getId());
    Optional<DriveEntity> actualDrive = driveRepository.findById(drive.getId());

    //then
    assertThat(actualFolder.isPresent()).isEqualTo(true);
    assertThat(actualFolder
        .get()
        .getFolderName()).isEqualTo("Child");

    System.out.println("Folders: " + actualDrive
        .get()
        .getFolders());
    System.out.println("Files: " + actualDrive
        .get()
        .getFiles());
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
