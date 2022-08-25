package com.wipro.cp.doconnect.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.Clock;
import java.util.Optional;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class ImageController {

	private final Path imageStorageDirectory;
	
	/* The target path can be configured in the application.properties */
	public ImageController(@Value("${image-storage-directory}") Path imageStorageDirectory) {
		this.imageStorageDirectory = imageStorageDirectory;
	}
	
	@PostConstruct
	public void ensureDirectoryExists() throws IOException {
		if (!Files.exists(this.imageStorageDirectory)) {
			Files.createDirectories(this.imageStorageDirectory);
		}
	}
	
	private static Optional<String> getFileExtension(String fileName) {
		final int indexOfLastDot = fileName.lastIndexOf('.');
		if (indexOfLastDot == -1) {
			return Optional.empty();
		} else {
			return Optional.of(fileName.substring(indexOfLastDot + 1));
		}
	}
	
	private static String generateFileName() {
		Random rnd = new Random();
	    int number = rnd.nextInt(99999);
		Clock clock = Clock.systemDefaultZone();
		long milliseconds = clock.millis();
		return String.format("%d%05d", milliseconds, number);
	}

	@PostMapping(value = "/images", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> uploadImage(@RequestPart(name = "image", required = true) MultipartFile imageFile) throws IOException {
		final String fileExtension = Optional.ofNullable(imageFile.getOriginalFilename()).flatMap(ImageController::getFileExtension).orElse("");
		final String targetFileName = generateFileName() + "." + fileExtension;
		final Path targetPath = this.imageStorageDirectory.resolve(targetFileName);
		try (InputStream in = imageFile.getInputStream()) {
			try (OutputStream out = Files.newOutputStream(targetPath, StandardOpenOption.CREATE)) {
				in.transferTo(out);
			}
		}
		return ResponseEntity.ok(targetFileName);
	}
	
	@GetMapping("/images/{fileName}")
	public ResponseEntity<Resource> downloadImage(@PathVariable("fileName") String fileName) {
		final Path targetPath = this.imageStorageDirectory.resolve(fileName);
		if (!Files.exists(targetPath)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(new PathResource(targetPath));
	}

}
