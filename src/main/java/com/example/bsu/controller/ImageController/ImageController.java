package com.example.bsu.controller.ImageController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@WebServlet("/api/images")
public class ImageController extends HttpServlet {
    private static final String IMAGES_PATH = "images";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uploadPath = request.getServletContext().getRealPath("") + File.separator + IMAGES_PATH;
        File uploadedDir = new File(uploadPath);
        if (!uploadedDir.exists()) {
            uploadedDir.mkdir();
        }

        try {
            Part filePart = request.getPart("image");
            String fileName = UUID.randomUUID().toString() + ".jpg";
            InputStream inputStream = filePart.getInputStream();
            File fileToSave = new File(uploadPath + File.separator + fileName);
            Files.copy(inputStream, fileToSave.toPath(), StandardCopyOption.REPLACE_EXISTING);

            response.setStatus(HttpServletResponse.SC_CREATED);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
