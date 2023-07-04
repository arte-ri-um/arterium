create database arterium;

use arterium;

CREATE TABLE role (
id int(11) NOT NULL AUTO_INCREMENT,
name varchar(20) NOT NULL,
PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE user (
id int AUTO_INCREMENT PRIMARY KEY,
password varchar(255) NOT NULL,
name varchar(20) NOT NULL,
nickname varchar(10) NOT NULL,
email varchar(50) NOT NULL,
phone varchar(11) NOT NULL,
birthdate datetime,
gender varchar(1) DEFAULT 'N',
profile_url text,
user_level int DEFAULT 1,
role int DEFAULT 30,
created_at datetime DEFAULT CURRENT_TIMESTAMP,
updated_at datetime DEFAULT CURRENT_TIMESTAMP
ON UPDATE CURRENT_TIMESTAMP,
INDEX idx_id (id)
);



CREATE TABLE oauth_user (
id int(11) NOT NULL AUTO_INCREMENT,
user_id int(11) NOT NULL,
provider varchar(50) NOT NULL,
provider_user_id varchar(255) NOT NULL,
access_token varchar(255) NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (user_id) REFERENCES user(id)
ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE refresh_token (
id bigint(20) NOT NULL AUTO_INCREMENT,
user_id int NOT NULL,
refresh_token varchar(255) NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (user_id) REFERENCES user(id)
ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

drop table exhibition;

-- exhibition 테이블 생성
CREATE TABLE exhibition (
id INT AUTO_INCREMENT PRIMARY KEY COMMENT '전시장 id',
name VARCHAR(50) COMMENT '전시장 이름',
location VARCHAR(100) COMMENT '전시장 주소',
holiday_info VARCHAR(20) COMMENT '휴관일 정보',
discount_info VARCHAR(200) COMMENT '할인 정보',
notice TEXT COMMENT '유의사항',
site_url VARCHAR(500) COMMENT '전시장 사이트 주소'
) COMMENT '전시장';

CREATE TABLE booking_site (
id INT AUTO_INCREMENT PRIMARY KEY COMMENT '예매처 아이디',
name VARCHAR(20) COMMENT '예매처 이름',
site_url VARCHAR(500) COMMENT '예매처 정식 사이트 정보',
icon VARCHAR(500) COMMENT '예매처 아이콘 사진'
) COMMENT '예매처';


drop table post;

CREATE TABLE post (
id INT AUTO_INCREMENT PRIMARY KEY COMMENT '포스트 아이디',
user_id int comment '작성자',
exhibition_id INT COMMENT '전시장 (exhibition id 외래키)',
start_date DATETIME COMMENT '시작일',
end_date DATETIME COMMENT '종료일',
viewing_time VARCHAR(50) COMMENT '관람시간',
age_restriction VARCHAR(50) COMMENT '관람연령',
price INT COMMENT '정가',
summary VARCHAR(500) COMMENT '요약',
description TEXT COMMENT '설명',
orign_url VARCHAR(500) COMMENT '전시 소개가 자세히 적힌 URL',
is_eligibility INT COMMENT '예매 가능 여부',
eligibility_date DATETIME COMMENT '티켓 오픈일, 예매 시작일',
reg_date DATETIME COMMENT '포스트 등록일',
post_url VARCHAR(500) COMMENT '포스트 URL',
foreign key (exhibition_id) references exhibition(id),
FOREIGN KEY (user_id) REFERENCES user(id)
) COMMENT '전시회 게시물';

-- review 테이블 생성
CREATE TABLE review (
id INT AUTO_INCREMENT PRIMARY KEY COMMENT '리뷰 식별자',
post_id INT COMMENT '게시물 식별자 (post id 외래키)',
user_id int COMMENT '게시자 (user id 외래키)',
content TEXT COMMENT '리뷰 내용',
created_at DATETIME COMMENT '리뷰 작성일시',
updated_at DATETIME COMMENT '리뷰 수정내용',
rating INT COMMENT '별점',
FOREIGN KEY (post_id) REFERENCES post(id),
FOREIGN KEY (user_id) REFERENCES user(id)
) COMMENT '전시회 리뷰 테이블';

CREATE TABLE booking_links (
post_id INT COMMENT '포스트 id',
site_id INT COMMENT '예매 사이트 id',
booking_url VARCHAR(500) COMMENT '예매 링크',
FOREIGN KEY (post_id) REFERENCES post(id),
FOREIGN KEY (site_id) REFERENCES booking_site(id)
) COMMENT '전시포스트-예매처 링크 관계 테이블';































