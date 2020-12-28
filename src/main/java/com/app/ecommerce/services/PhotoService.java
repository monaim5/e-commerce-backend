package com.app.ecommerce.services;

import com.app.ecommerce.exceptions.MonaimException;
import com.app.ecommerce.models.Photo;
import com.app.ecommerce.repositories.PhotoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@AllArgsConstructor
public class PhotoService {

    private final PhotoRepository photoRepository;

    public byte[] getPhoto(@PathVariable Long id) throws IOException {
        Photo photo = photoRepository.findById(id).orElseThrow(() -> new MonaimException("no such photo"));
        return Files.readAllBytes(Paths.get(
                System.getProperty("user.home") + "/documents/images/" + photo.getUrl())
        );
    }
}
