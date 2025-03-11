# File System
REST APIs for in-memory File System

## Requirements
Design and implement an in-memory file system:
- This file system consists of 3 entity types: Drives, Folders, and Files.
These entities, very much like their “real” file-system counterparts, obey the following relations:
  - A folder and a drive may contain zero to many other folders, or files.
  - A file does not contain any other entity.
  - A drive is not contained in any entity.
  - Any non-drive entity must be contained in another entity.

- The system should be capable of supporting at least the following file system like operations:
  - Create – Creates a new entity.
  - Delete – Deletes an existing entity (and all the entities it contains).
  - Move – Changing the parent of an entity.
  - Write – Changes the content of a file.

- As you design the API for these operations, you should consider the arguments to each and any
relevant exceptions that might occur.

## Tasks
- Design and implement the classes and APIs for this system.
- Part of the task is to think about what makes a usable API. 
- You should think about the appropriate properties for these entities as well.
- Implement unit tests that demonstrate the correctness of the API you’ve designed.
- State any assumptions you made and reasons for those assumptions.
- Be prepared to talk us through your design choices and implementation details.

## Tech Stack
- Java 21
- Spring Boot
- H2 database
- Spring Data JPA
- Swagger

## How to Run
- Install Java & Maven (I use SDKMAN!)
  - Java:
    ```
    sdk install java 21.0.6-tem 
    ```
    ```
    sdk default java 21.0.5-tem 
    ```
  - Maven:
    ```
    sdk install maven
    ```
- Compile the code:
  ```
  mvn clean compile
  ```

- Compile, test (all) and package the project:
  ```
  mvn clean install
  ```

- Run the application:
```
.\mvnw spring-boot:run
```

## Architecture Diagram
![file_system_arch](https://github.com/user-attachments/assets/ff8e55f9-9b87-469f-a9e1-c8298eeb5562)


## Unit Tests
<img width="1627" alt="Unit Tests" src="https://github.com/user-attachments/assets/725a4d3d-7f57-4b2f-b5a2-7b827b5b6b8d" />

## Swagger UI
### Drive Controller
<img width="1474" alt="Drive Controller" src="https://github.com/user-attachments/assets/3409de09-f6e0-4770-8790-d139cfac8fc4" />

### Folder Controller
<img width="1472" alt="Folder Controller" src="https://github.com/user-attachments/assets/b8218e27-5e87-49ca-8f74-917840c642dd" />

### File Controller
<img width="1467" alt="File Controller" src="https://github.com/user-attachments/assets/e28fd43d-6c32-4c22-9256-01e53a792844" />

## Version
| Library      | Version |
| -----------      | ----------- |
| IntelliJ IDEA CE | 2024.3.4.1    |
| Java             | 21.0.5 |
| Maven            | 3.9.8  |
| Spring Boot      | 3.4.3  |
| Spring Framework | 6.2.3 |
