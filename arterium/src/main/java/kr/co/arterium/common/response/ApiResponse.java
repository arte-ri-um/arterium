package kr.co.arterium.common.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiResponse {
    private HttpStatus status;
    private String message;
    private Object data;

    public static ApiResponse create(HttpStatus status, String message, Object data) {
        ApiResponse response = new ApiResponse();
        response.status = status;
        response.message = message;
        response.data = data;
        return response;
    }
}