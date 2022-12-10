INSERT INTO member (email, major, password, phone_number, student_id, user_name)
    VALUES ("ajou@ajou.ac.kr", "소프트웨어학과", "$2a$10$qGFCDjJ1quuk9HmuamHBXO8xV5l1bsUNwuelK87NZw.D8W.BUjMSG", "010-1234-5678", "201820772", "김우진");

update member set authority = "ROLE_NONMEMBER" where id = 1;

INSERT INTO club_auth_token (club_name, president_name, token)
    VALUES ("테스트 동아리2", "김진우", "11111");

INSERT INTO club_room(current_members, location)
    VALUES (0, "신학생회관 105호");

INSERT INTO club (category, club_name, introduce, room_id, president_name)
    VALUES ("코딩", "테스트 동아리1", "한 줄 소개입니다", 1, "김우진");

INSERT INTO club_member_list (authority, club_id, student_id)
    VALUES (3, 1, 1);

INSERT INTO club_img_file (file_url, filename, club_id) VALUES ("/home/ubuntu/img-files/", "chunsik.png", 1);