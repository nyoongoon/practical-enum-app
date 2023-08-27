-- 데이터베이스 생성
CREATE
DATABASE IF NOT EXISTS blog_example;

-- 사용할 데이터베이스 선택
USE
blog_example;

--
INSERT INTO post (title, content, created_at, category_code)
VALUES
    ('First Post', 'This is the content of the first post.', '2023-08-26 10:00:00', 'CT000001'),
    ('Second Post', 'Another post with some content.', '2023-08-26 14:30:00', 'CT000002');


INSERT INTO comment (post_id, content, created_at)
VALUES
    (1, 'Great post! Thanks for sharing.', '2023-08-26 10:30:00'),
    (1, 'I found this really helpful.', '2023-08-26 11:00:00'),
    (2, 'Interesting thoughts in this post.', '2023-08-26 15:00:00');


-- 예시 공통 코드 값 삽입
INSERT INTO common_code (code, description)
VALUES ('CT000001', 'Category A'),
       ('CT000002', 'Category B'),
       ('CT000003', 'Category C');

