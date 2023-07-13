package kr.co.arterium.domain.exhibition.controller;

import kr.co.arterium.domain.exhibition.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
@RestController
@RequiredArgsConstructor
public class FileController {   // 파일 업로드 설정

    private final FileService fileService;

    @PostMapping("/upload-image")
    @ResponseBody
    public ResponseEntity uploadImage(@RequestBody MultipartFile file){ //비동기로 이미지 업로드
        Map<String, Object> responseData = fileService.saveTempImage(file);     //이미지 임시 저장 및 정보 처리
        return ResponseEntity.ok(responseData); // 파일아이디와 url을 반환함
    }
}
