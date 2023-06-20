package com.kalidratorma.yss.file;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("file")
@RequiredArgsConstructor
class ContentFileController {

    private final FileLocationService fileLocationService;

    @GetMapping
    public List<ContentFile> readFiles() {
        return fileLocationService.findAllFiles();
    }

    @PostMapping
    List<ContentFile> uploadFile(@RequestParam MultipartFile... file) throws Exception {
        long maxSize;
        List<ContentFile> contentFiles = new ArrayList<>();
        for (MultipartFile lFile : file) {
            if (lFile.getContentType() == null) {
                throw new Exception("Unknown file format");
            } else if (lFile.getContentType().compareToIgnoreCase("video") >= 0) {
                maxSize = 100L * 1024L * 1024L; // 100 Mb
            } else if (lFile.getContentType().compareToIgnoreCase("image") >= 0) {
                maxSize = 10L * 1024L * 1024L; // 10 Mb
            } else {
                throw new Exception("Unknown file format");
            }
            if (lFile.getSize() > maxSize) {
                throw new Exception("File size is too large");
            }
            contentFiles.add(fileLocationService.save(lFile.getBytes(), lFile.getOriginalFilename()));
        }
        return contentFiles;
    }

    @GetMapping(value = "/{fileName}.{ext}")
    ResponseEntity<FileSystemResource> downloadImage(@PathVariable String fileName
            , @PathVariable String ext
    ) {
        FileSystemResource fileResource = fileLocationService.find(fileName + "." + ext);

        HttpHeaders responseHeaders = new HttpHeaders();
        String headerValue = switch (ext.toLowerCase()) {
            case "jpg", "jpeg", "png", "svg" -> "image/*";
            case "webm", "ogv", "mp4" -> "video/*";
            default -> "multipart/form-data";
        };

        responseHeaders.add("Content-Type"
                , headerValue);
        return new ResponseEntity<>(fileResource, responseHeaders, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteFile(@PathVariable Long id) {
        fileLocationService.deleteFileById(id);
        return HttpStatus.OK;
    }
}