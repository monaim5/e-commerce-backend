package com.app.ecommerce.services;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Stream;

@Service
public class PhotoStorageService implements FileStorageService{
    private final Path uploadsDir = Paths.get(System.getProperty("user.home"), "Documents/ecom assets/uploads");

    @Override
    public void init() throws IOException {
        Files.createDirectory(uploadsDir);
    }

    public Resource save(MultipartFile file) throws IOException {
        String filename = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date())  + ".jpg";
        this.save(file, filename);
        return this.load(filename);
    }

    @Override
    public void save(MultipartFile file, String filename) throws IOException {
        Path path = Paths.get(this.uploadsDir.toString(), filename);
        file.transferTo(new File(path.toString()));
    }

    @Override
    public Resource load(String filename) throws MalformedURLException {
        Path file = uploadsDir.resolve(filename);
        Resource resource = new UrlResource(file.toUri());

        if (resource.exists() || resource.isReadable()) {
            return resource;
        } else {
            throw new RuntimeException("Could not read the file!");
        }
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    }
}
