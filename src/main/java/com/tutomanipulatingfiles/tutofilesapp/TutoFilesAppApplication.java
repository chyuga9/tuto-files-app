package com.tutomanipulatingfiles.tutofilesapp;

import javax.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tutomanipulatingfiles.tutofilesapp.service.FilesStorageService;

@SpringBootApplication
public class TutoFilesAppApplication implements CommandLineRunner {
	
  @Resource
  FilesStorageService storageService;
  public static void main(String[] args) {
    SpringApplication.run(TutoFilesAppApplication.class, args);
  }
  
  @Override
  public void run(String... arg) throws Exception {
    storageService.deleteAll();
    storageService.init();
  }
}
