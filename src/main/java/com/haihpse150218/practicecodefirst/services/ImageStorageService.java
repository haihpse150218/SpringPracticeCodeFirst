package com.haihpse150218.practicecodefirst.services;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class ImageStorageService implements  IStorageService{
    private static  Path storageFolder = Paths.get("upload");
    //contructor

    public ImageStorageService() {
        try{
            Files.createDirectories(storageFolder);
        }catch (IOException ex){
            throw  new RuntimeException("Can not initialize storage",ex);
        }
    }
    private boolean isImageFile(MultipartFile file){
        //using FileNameUtils dependency commons-io
        //check extension
        String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
        return Arrays.asList(new String[]{"png","jpg","jpeg","bmp"})
                .contains(fileExtension.trim().toLowerCase());
    }

    @Override
    public String storeFIle(MultipartFile file) {
        try {
            if(file.isEmpty()){
                throw  new RuntimeException("Failed to store empty file.");
            }
            if(!isImageFile(file)){
                throw  new RuntimeException("You can only upload image file");
            }
            //file mus be <= 5Mb
            float fileSizeInMegaBytes = file.getSize() / 1000000;
            if(fileSizeInMegaBytes > 0.5f){
                throw  new RuntimeException("You can only upload image file <= 5Mb");
            }
            //file musbe rename in server
            String fileExtention = FilenameUtils.getExtension(file.getOriginalFilename());
            String generateFileName = UUID.randomUUID().toString().replace("-","");
            generateFileName = generateFileName+"."+ fileExtention;
            Path destinationFilePath = this.storageFolder.resolve(
                    Paths.get(generateFileName))
                .normalize().toAbsolutePath();
            if(!destinationFilePath.getParent().equals(this.storageFolder.toAbsolutePath())){
                throw new RuntimeException("Cannot store file outside current directory.");
            }
            try(InputStream inputStream = file.getInputStream()){
                Files.copy(inputStream,destinationFilePath, StandardCopyOption.REPLACE_EXISTING);
            }
            return generateFileName;
        }catch (IOException ex){
            throw  new RuntimeException("Failed to store file.");
        }
    }
    @Override
    public Stream<Path> loadAll() {
        return null;
    }

    @Override
    public byte[] readFileContent(String fileName) {
        return new byte[0];
    }

    @Override
    public void deleteAllFiles() {

    }
}
