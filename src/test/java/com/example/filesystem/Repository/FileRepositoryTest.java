package com.example.filesystem.Repository;

import com.example.filesystem.entity.DriveEntity;
import com.example.filesystem.entity.FileEntity;
import com.example.filesystem.entity.FolderEntity;
import com.example.filesystem.repository.DriveRepository;
import com.example.filesystem.repository.FileRepository;
import com.example.filesystem.repository.FolderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
    void givenThereExistNoDrive_createDrive_pass() {
        //given
        DriveEntity drive = new DriveEntity();
        drive.setDriveName("C");
        driveRepository.save(drive);

        //when
        Optional<DriveEntity> actual = driveRepository.findById(drive.getId());

        //then
        assertThat(actual.isPresent()).isEqualTo(true);
        assertThat(actual.get().getDriveName()).isEqualTo("C");
    }

    @Test
    void givenThereExistADrive_createDriveWithSameName_throwsException() {
        //given
        DriveEntity drive1 = new DriveEntity();
        drive1.setDriveName("C");
        driveRepository.save(drive1);

        DriveEntity drive2 = new DriveEntity();
        drive2.setDriveName("C");

        //when + then
        assertThatThrownBy(() -> driveRepository.save(drive2))
                .isInstanceOf(DataIntegrityViolationException.class)
                .hasMessageContaining("could not execute statement");
    }

    @Test
    void givenThereExistADrive_createFolder_pass() {
        //given
        DriveEntity drive = new DriveEntity();
        drive.setDriveName("C");
        driveRepository.save(drive);

        FolderEntity folder = new FolderEntity();
        folder.setDrive(drive);
        folder.setFolderName("Test");
        folderRepository.save(folder);

        //when
        Optional<FolderEntity> actual = folderRepository.findById(folder.getId());

        //then
        assertThat(actual.isPresent()).isEqualTo(true);
        assertThat(actual.get().getFolderName()).isEqualTo("Test");
    }

    @Test
    void givenThereExistNoDrive_createFolder_throwsException() {
        //given
        FolderEntity folder = new FolderEntity();
        folder.setFolderName("Test");

        //when + then
        assertThatThrownBy(() -> folderRepository.save(folder))
                .isInstanceOf(DataIntegrityViolationException.class)
                .hasMessageContaining("not-null property references a null or transient value");
    }

    @Test
    void givenThereExistAFolder_createFolder_pass() {
        //given
        DriveEntity drive = new DriveEntity();
        drive.setDriveName("C");
        driveRepository.save(drive);

        FolderEntity parentFolder = new FolderEntity();
        parentFolder.setDrive(drive);
        parentFolder.setFolderName("ParentFolder");
        folderRepository.save(parentFolder);

        FolderEntity childFolder = new FolderEntity();
        childFolder.setDrive(drive);
        childFolder.setParentFolder(parentFolder);
        childFolder.setFolderName("ChildFolder");
        folderRepository.save(childFolder);

        //when
        Optional<FolderEntity> actualFolder = folderRepository.findById(childFolder.getId());

        //then
        assertThat(actualFolder.isPresent()).isEqualTo(true);
        assertThat(actualFolder.get().getDrive().getDriveName()).isEqualTo("C");
        assertThat(actualFolder.get().getParentFolder().getFolderName()).isEqualTo("ParentFolder");
        assertThat(actualFolder.get().getFolderName()).isEqualTo("ChildFolder");
    }

    @Test
    void givenThereExistADrive_createFile_pass() {
        //given
        DriveEntity drive = new DriveEntity();
        drive.setDriveName("C");
        driveRepository.save(drive);

        FileEntity file = new FileEntity();
        file.setDrive(drive);
        file.setFileName("Faran.txt");
        fileRepository.save(file);

        //when
        Optional<FileEntity> actual = fileRepository.findById(file.getId());

        //then
        assertThat(actual.isPresent()).isEqualTo(true);
        assertThat(actual.get().getFileName()).isEqualTo("Faran.txt");
    }

    @Test
    void givenThereExistAFolder_createFile_pass() {
        //given
        DriveEntity drive = new DriveEntity();
        drive.setDriveName("C");
        driveRepository.save(drive);

        FolderEntity folder = new FolderEntity();
        folder.setDrive(drive);
        folder.setFolderName("Test");
        folderRepository.save(folder);

        FileEntity file = new FileEntity();
        file.setDrive(drive);
        file.setFolder(folder);
        file.setFileName("Faran.txt");
        fileRepository.save(file);

        //when
        Optional<FileEntity> actual = fileRepository.findById(file.getId());

        //then
        assertThat(actual.isPresent()).isEqualTo(true);
        assertThat(actual.get().getDrive().getDriveName()).isEqualTo("C");
        assertThat(actual.get().getFolder().getFolderName()).isEqualTo("Test");
        assertThat(actual.get().getFileName()).isEqualTo("Faran.txt");
    }
}
