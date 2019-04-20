package com.apibatdongsan.batdongsandanang.controller;

import com.apibatdongsan.batdongsandanang.dto.ApiResponseDTO;
import com.apibatdongsan.batdongsandanang.dto.PostRequestDTO;
import com.apibatdongsan.batdongsandanang.entity.Picture;
import com.apibatdongsan.batdongsandanang.entity.PictureEntity;
import com.apibatdongsan.batdongsandanang.entity.Post;
import com.apibatdongsan.batdongsandanang.respository.PicturesRepository;
import com.apibatdongsan.batdongsandanang.service.FileStorageService;
import com.apibatdongsan.batdongsandanang.service.PictureService;
import com.apibatdongsan.batdongsandanang.service.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 1800)

public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private PictureService pictureService;

    @Autowired
    private PostService postService;


    @PostMapping("/api/picture")
    public ApiResponseDTO uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("idPost") Long idPost) {
        Picture pictureAdded = fileStorageService.storeFile(file, idPost);
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());



        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        String responInfor =  fileName + " " +  fileDownloadUri + " " + file.getContentType() + " " + file.getSize();
        return new ApiResponseDTO(200,"Created!", pictureAdded );
    }

    @GetMapping("/api/images/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) throws IOException {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);
        File fileImage = resource.getFile();

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }

    @DeleteMapping(value = "/api/post/picture/{id}")
    public ResponseEntity<Boolean> deletePictureById(@PathVariable("id") Long id) {
        pictureService.deletePictureById(id);
        return ResponseEntity.ok(true);
    }

    @PostMapping(value = "/api/post/pictures/{idPost}")
    @Consumes({"application/json", MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Object> addNewPictureForPost(@PathVariable("idPost") Long idPost, @RequestParam(value = "files", required = false) MultipartFile[] files) {

        ObjectMapper mapper = new ObjectMapper();
        try {
            Post post = postService.findById(idPost);
            postService.addImages(post, files, null);
            List<PictureEntity> pictures = postService.findPicturesById(idPost);
            return ResponseEntity.ok(pictures);
        } catch (InternalError | NullPointerException  e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


}
