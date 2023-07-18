package kr.co.arterium.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
@PropertySource(value= {"classpath:application.yml"})
public class WebConfig implements WebMvcConfigurer {    //외부 경로 resource 접근을 위한 설정

    private String connectPath;
    private String resourcePath;

    @Value("${fileLocation.image}")
    public void setResourcePath(String fileLocation) {  //yml에 설정한 파일 경로 주입 - bean 생성 후 주입해야 함
        connectPath = FileLocation.TEMP_IMAGE.getLocation()+"**";
        resourcePath = "file:" + fileLocation;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) { // 요청에따른 외부 경로 지정
        registry.addResourceHandler(connectPath)
                .addResourceLocations(resourcePath);
    }
}