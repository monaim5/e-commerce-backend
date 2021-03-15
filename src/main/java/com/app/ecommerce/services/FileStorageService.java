package com.app.ecommerce.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface FileStorageService {
    void init() throws IOException;

    void save(MultipartFile file, String fileName) throws IOException;

    Resource load(String filename) throws MalformedURLException;

    void deleteAll();

    Stream<Path> loadAll();
}
