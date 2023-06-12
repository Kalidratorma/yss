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

    String save(byte[] bytes, String imageName) throws Exception {
        String date = Long.toString(new Date().getTime()/1000);
        String imageNameWithDate = date + "-" + imageName;
        String location = fileSystemRepository.save(bytes, imageNameWithDate);
        contentFileRepository.save(new ContentFile(imageNameWithDate, location));

        return "/file/" + imageNameWithDate;
    }

    FileSystemResource find(String fileName) {
        ContentFile contentFile = contentFileRepository
                .findTopByNameOrderByIdDesc(fileName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return fileSystemRepository.findInFileSystem(contentFile.getLocation());
    }

    void deleteFileBySiteName(String fileName) {

        contentFileRepository
                .deleteByName(fileName);
    }
}