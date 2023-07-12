package kr.co.arterium.config;

public enum FileLocation {
    TEMP_IMAGE("classpath:/static/img/temp/"),    // 임시 이미지 파일의 위치
    BOARD_ATTACHMENT("board-attachments/");       // 게시판 첨부 파일의 위치

    private final String location;

    /**
     * FileLocation 열거형의 생성자입니다.
     *
     * @param location 파일의 위치를 나타내는 문자열
     */
    FileLocation(String location) {
        this.location = location;
    }

    /**
     * 파일의 위치를 반환합니다.
     *
     * @return 파일의 위치
     */
    public String getLocation() {
        return location;
    }
}