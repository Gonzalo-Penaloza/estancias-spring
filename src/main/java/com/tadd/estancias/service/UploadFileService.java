package com.tadd.estancias.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileService {
	private final String route = "src\\main\\resources\\static\\";
	private Map<String, String> folderMap = new HashMap<>();

    public UploadFileService() {
        // Inicialización del mapa con las rutas por defecto
        folderMap.put("default", "images//");
        folderMap.put("user", "images//user//");
        folderMap.put("casa", "images//casa//");
    }
	
	public String saveImage(MultipartFile file, String entity) throws IOException {
		if(!file.isEmpty()) {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(route + folderMap.getOrDefault(entity, folderMap.get("default")) + file.getOriginalFilename());	
            Files.write(path, bytes);
            
            return file.getOriginalFilename();
		}
			
		return "default.jpg";
	}
	
	public void deleteImage(String nombreImg, String entity) {		
		File file = new File(folderMap.get(entity) + nombreImg);
		
		file.delete();
	}
	
}
