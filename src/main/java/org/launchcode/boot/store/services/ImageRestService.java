package org.launchcode.boot.store.services;


import org.launchcode.boot.store.exceptions.FileStorageException;
import org.launchcode.boot.store.exceptions.MyFileNotFoundException;
import org.launchcode.boot.store.models.ImageFile;
import org.launchcode.boot.store.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Service
@Deprecated
public class ImageRestService {
//    static final String HOST = "http://localhost:8080";
    //    static final String NP_HOST = "https://mystoreapi-np.ag.amazon.com";
//    static final String PROD_HOST = "https://mystoreapi.ag.amazon.com";
//    static final String BASE_URL = HOST+"/store/rest";
//    static final String IMAGE_URL = BASE_URL+"/image";

//    @Autowired
//    private ImageRepository imageRepository;

//    private RestTemplate template = new RestTemplate();

//    public ImageFile storeFile(MultipartFile file) {
//        // Normalize file name
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//
//        try {
//            // Check if the file's name contains invalid characters
//            if(fileName.contains("..")) {
//                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
//            }
//
//            ImageFile imageFile = new ImageFile(fileName, file.getContentType(), "Radhika", file.getBytes());
//
//            return imageRepository.save(imageFile);
//        } catch (IOException ex) {
//            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
//        }
//    }

//    public ImageFile getImageFile(int fileId) {
//        return template.getForObject(IMAGE_URL+"/"+fileId, ImageFile.class);
//    }
}
