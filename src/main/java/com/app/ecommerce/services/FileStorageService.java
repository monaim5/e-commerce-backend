package com.app.ecommerce.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface FileStorageService {
    public void init() throws IOException;

    public void save(MultipartFile file, String fileName) throws IOException;

    public Resource load(String filename) throws MalformedURLException;

    public void deleteAll();

    public Stream<Path> loadAll();
}
