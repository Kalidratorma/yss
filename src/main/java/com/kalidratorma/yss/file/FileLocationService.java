package com.kalidratorma.yss.file;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
class FileLocationService {

    private final FileSystemRepository fileSystemRepository;

    private final ContentFileRepository contentFileRepository;

    List<ContentFile> findAllFiles() {
        return contentFileRepository.findAll();
    }

    ContentFile save(byte[] bytes, String imageName) throws Exception {
        String uuid = java.util.UUID.randomUUID().toString();
        String imageNameWithDate = uuid + "-" + imageName;
        String location = fileSystemRepository.save(bytes, imageNameWithDate);
        return contentFileRepository.save(new ContentFile(imageNameWithDate, location));
    }

    FileSystemResource find(String fileName) {
        ContentFile contentFile = contentFileRepository
                .findTopByNameOrderByIdDesc(fileName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return fileSystemRepository.findInFileSystem(contentFile.getLocation());
    }

    void deleteFileById(Long id) {

        contentFileRepository
                .deleteById(id);
    }
}