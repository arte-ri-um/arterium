package kr.co.arterium.domain.exhibition.service;

import kr.co.arterium.config.FileLocation;
import kr.co.arterium.domain.exhibition.entity.ImageTempEntity;
import kr.co.arterium.domain.exhibition.repository.ImageTempRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final ImageTempRepository imageTempRepository;
    private final ResourceLoader resourceLoader;
    @Override
    public Map<String, Object> saveTempImage(MultipartFile file) {
        //파일 정보
        FileLocation fileLocation = FileLocation.TEMP_IMAGE;
        String originalName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileExt = getFileExtension(originalName);
        String saveName = generateSaveName(fileExt);
        long fileSize = file.getSize();
        LocalDateTime registerTime = LocalDateTime.now();
        Path uploadPath =null;
        Path filePath = null;
        //파일 저장
        try {
            uploadPath = resourceLoader.getResource(fileLocation.getLocation()).getFile().toPath();
            filePath = uploadPath.resolve(saveName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("클래스패스 리소스의 절대 파일 경로를 얻을 수 없습니다.", e);
        } catch (IOException e) {
            throw new RuntimeException("파일 저장이 정상 처리되지 않았습니다.", e);
        }

        // DB저장
        ImageTempEntity imageTempEntity = ImageTempEntity.builder()
                .originalName(originalName)
                .saveName(saveName)
                .fileExt(fileExt)
                .fileSize(fileSize)
                .registerDate(registerTime)
                .build();
        imageTempRepository.save(imageTempEntity);

        // 파일 db id와 url 회신
        Map<String,Object> response = new HashMap<>();
        response.put("fileId", imageTempEntity.getId());
        response.put("fileUrl", "/static/img/temp/" + saveName);

        return response;
    }
    private static String getFileExtension(String filename) {
        if (filename == null) {
            return "";
        }
        int dotIndex = filename.lastIndexOf(".");
        if (dotIndex < 0 || dotIndex == filename.length() - 1) {
            return "";
        }
        return filename.substring(dotIndex + 1).toLowerCase();
    }

    private static String generateSaveName(String extension) {
        String randomUUID = UUID.randomUUID().toString();
        LocalDateTime now = LocalDateTime.now();
        String timestamp = now.toString().replace(":", "-");
        return randomUUID + "_" + timestamp + "." + extension;
    }
}
