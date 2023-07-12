package kr.co.arterium.domain.exhibition.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface FileService {
    Map<String, Object> saveTempImage(MultipartFile file); //파일 저장 처리
}
