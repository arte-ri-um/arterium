-- 로컬 testDB에 DB 만들고 시작하기

CREATE TABLE role
(
    id bigint NOT NULL AUTO_INCREMENT,
    name varchar(20) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE users (
                       id bigint AUTO_INCREMENT,
                       password varchar(255) NOT NULL,
                       nickname varchar(20) NOT NULL UNIQUE,
                       email varchar(50) NOT NULL UNIQUE,
                       phone varchar(11) NOT NULL UNIQUE,
                       birthdate datetime,
                       gender varchar(1) DEFAULT 'N',
                       profile_url text,
                       user_level int DEFAULT 1,
                       role_id bigint DEFAULT 30,
                       created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

                       PRIMARY KEY (id)
);

CREATE TABLE oauth_user (
                            id bigint NOT NULL AUTO_INCREMENT,
                            user_id bigint NOT NULL,
                            provider varchar(50) NOT NULL,
                            provider_user_id varchar(255) NOT NULL,
                            access_token varchar(255) NOT NULL,

                            PRIMARY KEY (id),
                            FOREIGN KEY (user_id) REFERENCES users(id)
                                ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE refresh_token (
                               id bigint NOT NULL AUTO_INCREMENT,
                               user_id bigint NOT NULL,
                               refresh_token varchar(255) NOT NULL,

                               PRIMARY KEY (id),
                               FOREIGN KEY (user_id) REFERENCES users(id)
                                   ON DELETE CASCADE ON UPDATE CASCADE
);

-- exhibition 테이블 생성
CREATE TABLE exhibition (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '전시장 id',
                            name VARCHAR(50) COMMENT '전시장 이름',
                            location VARCHAR(100) COMMENT '전시장 주소',
                            holiday_info VARCHAR(20) COMMENT '휴관일 정보',
                            discount_info VARCHAR(200) COMMENT '할인 정보',
                            notice TEXT COMMENT '유의사항',
                            site_url VARCHAR(500) COMMENT '전시장 사이트 주소'
);

CREATE TABLE booking_site (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '예매처 아이디',
                              name VARCHAR(20) COMMENT '예매처 이름',
                              site_url VARCHAR(500) COMMENT '예매처 정식 사이트 정보',
                              icon VARCHAR(500) COMMENT '예매처 아이콘 사진'
);

CREATE TABLE post (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '포스트 아이디',
                      user_id BIGINT comment '작성자',
                      exhibition_id BIGINT COMMENT '전시장 (exhibition id 외래키)',
                      start_date DATETIME COMMENT '시작일',
                      end_date DATETIME COMMENT '종료일',
                      viewing_time VARCHAR(50) COMMENT '관람시간',
                      age_restriction VARCHAR(50) COMMENT '관람연령',
                      price INT COMMENT '정가',
                      summary VARCHAR(500) COMMENT '요약',
                      description TEXT COMMENT '설명',
                      origin_url VARCHAR(500) COMMENT '전시 소개가 자세히 적힌 URL',
                      is_eligibility INT COMMENT '예매 가능 여부',
                      eligibility_date DATETIME COMMENT '티켓 오픈일, 예매 시작일',
                      reg_date DEFAULT CURRENT_TIMESTAMP COMMENT '포스트 등록일',
                      post_url VARCHAR(500) COMMENT '포스트 URL',

                      foreign key (exhibition_id) references exhibition(id),
                      FOREIGN KEY (user_id) REFERENCES users(id)
);

-- review 테이블 생성
CREATE TABLE review (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '리뷰 식별자',
                        post_id BIGINT COMMENT '게시물 식별자 (post id 외래키)',
                        user_id BIGINT COMMENT '게시자 (user id 외래키)',
                        content TEXT COMMENT '리뷰 내용',
                        created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '리뷰 작성일시',
                        updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '리뷰 수정일시',
                        rating INT COMMENT '별점',
                        likes INT DEFAULT 0 COMMENT '추천수',
                        FOREIGN KEY (post_id) REFERENCES post(id),
                        FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE booking_links (
                               post_id BIGINT COMMENT '포스트 id',
                               site_id BIGINT COMMENT '예매 사이트 id',
                               booking_url VARCHAR(500) COMMENT '예매 링크',
                               FOREIGN KEY (post_id) REFERENCES post(id),
                               FOREIGN KEY (site_id) REFERENCES booking_site(id)
);

