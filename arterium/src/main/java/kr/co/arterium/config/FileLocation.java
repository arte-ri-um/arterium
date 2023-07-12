package kr.co.arterium.config;

public enum FileLocation {
    TEMP_IMAGE("classpath:/static/img/temp/"),
    BOARD_ATTACHMENT("board-attachments/");

    private final String location;

    FileLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }
}
