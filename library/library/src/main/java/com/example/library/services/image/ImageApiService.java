package com.example.library.services.image;

import com.example.library.entities.avatarBooks.AvatarBooks;
import com.example.library.repositories.avatarBooks.AvatarBooksRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageApiService implements ImageService{

    private final AvatarBooksRepository avatarBooksRepository;

    @Override
    @SneakyThrows
    public Long saveImageForBooks(Long id, MultipartFile image) {
        return avatarBooksRepository.save(new AvatarBooks(id, image.getBytes())).getId();
    }

    @Override
    public byte[] getImageForBooks(Long id) {
        return avatarBooksRepository.findById(id)
                .orElseThrow(NoSuchElementException::new).getImage();
    }

}
