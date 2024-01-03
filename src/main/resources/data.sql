-- 데이터베이스 생성
CREATE DATABASE IF NOT EXISTS blog_example;

-- 사용할 데이터베이스 선택
USE blog_example;

DROP TABLE IF EXISTS post_history;
DROP TABLE IF EXISTS post;
DROP TABLE IF EXISTS common_code;
DROP TABLE IF EXISTS child;
DROP TABLE IF EXISTS parent;


# CREATE TABLE common_code (
#                              code VARCHAR(255) PRIMARY KEY,
#                              description VARCHAR(255) NOT NULL
# );

CREATE TABLE post (
                      post_no INT AUTO_INCREMENT,
                      some_code VARCHAR(255),
                      title VARCHAR(255),
                      content TEXT,
                      created_at DATETIME,
                      category_code VARCHAR(255),
                      delete_type CHAR(1),
                      expenditure_code VARCHAR(255),
                      industry_code VARCHAR(255),
                      PRIMARY KEY (post_no, some_code)
);


CREATE TABLE post_history (
                         post_history_no INT AUTO_INCREMENT,
                         post_no INT NOT NULL,
                         some_code VARCHAR(255),
                         content TEXT,
                         created_at DATETIME,
                         PRIMARY KEY (post_history_no, post_no, some_code),
                         FOREIGN KEY (post_no, some_code) REFERENCES post(post_no, some_code)
);

-- Parent 테이블 생성
CREATE TABLE parent (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT,
                        name VARCHAR(255)
);

-- Child 테이블 생성
CREATE TABLE child (
                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                       description VARCHAR(255),
                       parent_id BIGINT,
                       FOREIGN KEY (parent_id) REFERENCES parent(id)
);

-- 예시 공통 코드 값 삽입
# INSERT INTO common_code (code, description)
# VALUES
#     ('CT000001', 'CustomerInquiry'),
#     ('CT000002', 'FreeBoard'),
#     ('CT000003', 'RefundInquiry'),
#     ('CT000004', 'TechSupport'),
#     ('CT000005', 'ProductReviews'),
#     ('CT000006', 'Discussions'),
#     ('CT000007', 'UserManuals'),
#     ('EC000001', 'FOOD'),
#     ('EC000002', 'TRANSPORTATION'),
#     ('EC000003', 'HOUSING'),
#     ('IC000001', 'TECHNOLOGY'),
#     ('IC000002', 'FINANCE'),
#     ('IC000003', 'HEALTHCARE');

# -- 게시물 삽입
# INSERT INTO post (some_code, title, content, created_at, category_code, delete_type, expenditure_code, industry_code)
# VALUES
#     ('12341234', 'First Post','his is the content of the first post.', '2023-08-26 10:00:00', 'CT000001', 'N', 'EC000002', 'IC000002'),
#     ('43214321', 'Second Post', 'Another post with some content.', '2023-08-26 14:30:00', 'CT000002', 'N', 'EC000001', 'IC000003');
#
# -- 댓글 삽입
# INSERT INTO comment (post_id, content, created_at)
# VALUES
#     (1, 'Great post! Thanks for sharing.', '2023-08-26 10:30:00'),
#     (1, 'I found this really helpful.', '2023-08-26 11:00:00'),
#     (2, 'Interesting thoughts in this post.', '2023-08-26 15:00:00');
