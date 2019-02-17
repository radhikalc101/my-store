package org.launchcode.boot.store.rest;


import org.launchcode.boot.store.exceptions.FileStorageException;
import org.launchcode.boot.store.models.ImageFile;
import org.launchcode.boot.store.repositories.ImageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "store/rest" )
public class ImageRestController {

    private static final Logger logger = LoggerFactory.getLogger(ImageRestController.class);

    @Autowired
    private ImageRepository imageRepository;

    @GetMapping(value = "loadImages")
    public Iterable<ImageFile> loadImages(Model model) {
        return imageRepository.findAll();
    }

    @GetMapping(value = "image/{imageId}")
    public ImageFile getImageFile(@PathVariable int imageId){
        return imageRepository.findById(imageId).get();
    }

    @PostMapping("/uploadFile")
    public ImageFile uploadStoreFile(@RequestParam("file") MultipartFile file)  throws IOException {
        return storeFile(file);
    }

    private ImageFile storeFile(MultipartFile file) throws IOException{
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            ImageFile imageFile = new ImageFile(fileName, file.getContentType(), "Radhika", file.getBytes());

            return imageRepository.save(imageFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    @PostMapping("/uploadMultipleFiles")
    public List<ImageFile> uploadMultipleStoreFiles(@RequestParam("files") MultipartFile[] files) throws IOException{
        return Arrays.asList(files)
                .stream()
                .map(file -> {
                    try {
                        return storeFile(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .collect(Collectors.toList());
    }


}
