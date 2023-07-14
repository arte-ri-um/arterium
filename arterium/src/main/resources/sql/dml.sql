SELECT * FROM users;

-- POST 테스트용 USER 생성
INSERT INTO users (password, nickname, email, phone, birthdate, gender, profile_url, user_level, role_id)
VALUES
    ('password1', 'user1', 'user1@example.com', '01012345678', '1990-01-01', 'M', 'http://example.com/profile1', 1, 30),
    ('password2', 'user2', 'user2@example.com', '01023456789', '1995-02-02', 'F', 'http://example.com/profile2', 2, 30),
    ('password3', 'user3', 'user3@example.com', '01034567890', '2000-03-03', 'M', 'http://example.com/profile3', 1, 30),
    ('password4', 'user4', 'user4@example.com', '01045678901', '2005-04-04', 'F', 'http://example.com/profile4', 2, 30);
