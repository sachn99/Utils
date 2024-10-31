package com.pdfgen.repository;

import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Repository
public class PDFRepository {
    private static final String STORAGE_DIRECTORY = "pdf_storage/";

    public PDFRepository() {
        new File(STORAGE_DIRECTORY).mkdirs();
    }

    public File getFile(String fileName) {
        return new File(STORAGE_DIRECTORY + fileName);
    }

    public byte[] readFileToByteArray(File file) {
        try {
            return Files.readAllBytes(Paths.get(file.getAbsolutePath()));
        } catch (IOException e) {
            throw new RuntimeException("Could not read PDF file: " + file.getName(), e);
        }
    }
}
