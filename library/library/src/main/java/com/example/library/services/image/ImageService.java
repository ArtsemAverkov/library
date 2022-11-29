package com.example.library.services.image;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    Long saveImageForBooks(Long id, MultipartFile image);
    byte[] getImageForBooks(Long id);

}
