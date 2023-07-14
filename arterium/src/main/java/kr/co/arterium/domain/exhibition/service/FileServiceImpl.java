package kr.co.arterium.domain.exhibition.service;

import kr.co.arterium.config.FileLocation;
import kr.co.arterium.domain.exhibition.entity.ImageTempEntity;
import kr.co.arterium.domain.exhibition.repository.ImageTempRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final ImageTempRepository imageTempRepository;

    @Override
    public Map<String, Object> saveTempImage(MultipartFile image) {  //파일 저장 처리

        // TODO 파일이 없으면 에러처리
        
        //파일 정보
        String originalName = StringUtils.cleanPath(image.getOriginalFilename());   //파일 원래 이름(폴더를 생성해서 저장하지 않으므로 이름을 변경
        String fileExt = getFileExtension(originalName);    //확장자
        String saveName = generateSaveName(fileExt);        //저장이름
        long fileSize = image.getSize();    // 사이즈저장
        LocalDateTime registerTime = LocalDateTime.now();   //등록시간
        String fileUrl=FileLocation.TEMP_IMAGE.getLocation()+saveName;
        // TODO 확장자 검사 - 확장자명이 없는 경우 error, png와 jpg가 아닌 경우 error
        File file = new File(saveName);
        try {
            image.transferTo(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
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
        response.put("fileUrl", fileUrl);

        return response;
    }
    private static String getFileExtension(String filename) {   // 확장자 처리
        if (filename == null) {
            return "";
        }
        int dotIndex = filename.lastIndexOf(".");
        if (dotIndex < 0 || dotIndex == filename.length() - 1) {
            return "";
        }
        return filename.substring(dotIndex + 1).toLowerCase();
    }

    private static String generateSaveName(String extension) {  // 저장 이름 만들기
        String randomUUID = UUID.randomUUID().toString();   //랜덤명 저장
        LocalDateTime now = LocalDateTime.now();            //현재시간
        String timestamp = now.toString().replace(":", "-");
        return randomUUID + "_" + timestamp + "." + extension;  // 새로 저장할 이름
    }
}
