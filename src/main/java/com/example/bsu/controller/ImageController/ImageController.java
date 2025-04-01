package com.example.bsu.controller.ImageController;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 5,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5
)
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
            JSONObject jsonObject = new JSONObject();
            Part filePart = request.getPart("file");
            String fileName = UUID.randomUUID().toString() + ".jpg";
            InputStream inputStream = filePart.getInputStream();
            File fileToSave = new File(uploadPath + File.separator + fileName);
            Files.copy(inputStream, fileToSave.toPath(), StandardCopyOption.REPLACE_EXISTING);
            response.setStatus(HttpServletResponse.SC_CREATED);
            response.setContentType("application/json");
            jsonObject.put("message", fileName);
            response.getWriter().write(jsonObject.toJSONString());
        } catch (ServletException | IOException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
