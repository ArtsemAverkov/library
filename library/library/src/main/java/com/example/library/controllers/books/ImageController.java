package com.example.library.controllers.books;

import com.example.library.services.image.ImageService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    /**
     * this method save image for books by id
     * @param idBook get from server
     * @param image get from local
     * @returт successful and unsuccessful save
     */

    @PostMapping("{Id}/imageForBooks")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Long saveImageForModelProduct(@PathVariable Long idBook,
                                         @RequestBody @NonNull MultipartFile image){
        return imageService.saveImageForBooks(idBook, image);
    }

    /**
     * this method get image for book by id
     * @param idBook get from server
     * @returт image
     */

    @GetMapping(value = "{Id}/imageForBooks", produces = MediaType.IMAGE_PNG_VALUE)
    public  byte[] getImageForModelProduct(@PathVariable Long idBook){
        return imageService.getImageForBooks(idBook);
    }
}
