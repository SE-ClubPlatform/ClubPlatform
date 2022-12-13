INSERT INTO member (email, major, password, phone_number, student_id, user_name)
    VALUES ("ajou@ajou.ac.kr", "소프트웨어학과", "$2a$10$qGFCDjJ1quuk9HmuamHBXO8xV5l1bsUNwuelK87NZw.D8W.BUjMSG", "010-1234-5678", "201820772", "김우진");

update member set authority = "ROLE_NONMEMBER" where id = 1;

INSERT INTO club_auth_token (club_name, president_name, token)
    VALUES ("테스트 동아리2", "김진우", "11111");

INSERT INTO club_room (current_members, location) VALUES (0,'학생회관 2229호');
INSERT INTO club_room (current_members, location) VALUES (0,'학생회관 1216호');
INSERT INTO club_room (current_members, location) VALUES (0,'학생회관 1234호');
INSERT INTO club_room (current_members, location) VALUES (0,'학생회관1 316호');
INSERT INTO club_room (current_members, location) VALUES (0,'학생회관2 406호');

INSERT INTO club (category,club_name,introduce,president_name,room_id) VALUES ('코딩 교육/봉사','SWeat','C, Python, Java, 웹, 아두이노, 라즈베리 파이 등 여러 가지 주제로 교육봉사 진행','이준수',1);
INSERT INTO club (category,club_name,introduce,president_name,room_id) VALUES ('스쿼시','꽁','이색 스포츠 즐기실 분, 다이어트 하고 싶으신 분, 스쿼시 좋아하시는 분 누구든 환영합니다!','이상훈',2);
INSERT INTO club (category,club_name,introduce,president_name,room_id) VALUES ('프로그래밍','DoIT','컴퓨터를 어려워하는 학생들이 컴퓨터와 쉽게 소통할 수 있도록 징검다리 역할을 수행하는 것이 목적입니다.','신우현',3);
INSERT INTO club (category,club_name,introduce,president_name,room_id) VALUES ('커버댄스','마스터피스','춤을 추고자 하는 사람들은 누구나 입회 가능하며, 대학에서 춤을 통해 즐거운 추억을 쌓아갈 수 있는 동아리입니다.','박지영',4);
INSERT INTO club (category,club_name,introduce,president_name,room_id) VALUES ('아주냥이 집사','미유미유','사람과 고양이가 조화롭게 공존하는 사회를 만드는 것을 목표로 활동하고 있습니다.','김우진',5);

INSERT INTO club_member_list (authority, club_id, student_id)
    VALUES (3, 5, 1);

INSERT INTO club_img_file (file_url, filename, club_id) VALUES ("C:/image/", "sweat.png", 1);
INSERT INTO club_img_file (file_url, filename, club_id) VALUES ("C:/image/", "kkong.png", 2);
INSERT INTO club_img_file (file_url, filename, club_id) VALUES ("C:/image/", "chunsik.png", 3);
INSERT INTO club_img_file (file_url, filename, club_id) VALUES ("C:/image/", "chunsik.png", 4);
INSERT INTO club_img_file (file_url, filename, club_id) VALUES ("C:/image/", "chunsik.png", 5);