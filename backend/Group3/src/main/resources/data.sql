INSERT INTO member (email, major, password, phone_number, student_id, user_name)
    VALUES ("dnwls813@ajou.ac.kr", "소프트웨어학과", "$2a$10$qGFCDjJ1quuk9HmuamHBXO8xV5l1bsUNwuelK87NZw.D8W.BUjMSG", "010-1234-5678", "201820772", "김우진");

INSERT INTO club (category, club_name, introduce, president_name)
    VALUES ("코딩 동아리", "우진이의 동아리", "한 줄 소개입니다", "김우진");

INSERT INTO club_auth_token (club_name, president_name, token)
    VALUES ("테스트 동아리", "김우진", "11111");