package com.example.filesystem;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(
    title = "File System", version = "1.0", description = "REST API for in-memory File System"))
@SpringBootApplication
public class FilesystemApplication {

  public static void main(String[] args) {
    SpringApplication.run(FilesystemApplication.class, args);
  }

}