package com.project.platform.service;

import com.project.platform.dto.FileInfoDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface FileService {

    FileInfoDTO upload(MultipartFile file, String bucket) throws IOException;

    File getFile(FileInfoDTO fileInfoDTO, Integer width, Integer height) throws IOException;
}
