package com.company.shifo24.service.media;

import com.company.shifo24.domains.entity.Media;
import com.company.shifo24.exception.ItemNotFoundException;
import com.company.shifo24.repository.MediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MediaService {

    private final Path fileLocation;

    @Autowired
    private MediaRepository mediaRepository;

    public MediaService() {
        String fileUploadDir = "C:\\JAVA\\RealProjects\\Shifo24\\src\\main\\resources\\uploads";
        this.fileLocation = Paths.get(fileUploadDir)
                .toAbsolutePath().normalize();
    }

    public Media saveFile(MultipartFile file) {
        String fullFileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            Path targetLocation = fileLocation.resolve(fullFileName);
            Files.copy(file.getInputStream(), targetLocation);
        } catch (FileAlreadyExistsException e) {
            String[] fileNameAndType = fullFileName.split("\\.");
            fullFileName = fileNameAndType[0] + System.currentTimeMillis() + "." + fileNameAndType[1];
            Path targetLocation = fileLocation.resolve(fullFileName);
            try {
                Files.copy(file.getInputStream(), targetLocation);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Media media = Media.builder()
                .originalName(file.getOriginalFilename())
                .fileDownloadUri(fileLocation + "\\" + file.getOriginalFilename())
                .build();

        return mediaRepository.save(media);
    }


    public Path downloadFile(Long fileId) {
        Media media = getMediaById(fileId);
        return fileLocation.resolve(media.getOriginalName());
    }

    public Media getMediaById(Long mediaID) {
        return mediaRepository.findById(mediaID).orElseThrow(
                () -> new ItemNotFoundException("Media not found with ID: " + mediaID)
        );
    }
}
