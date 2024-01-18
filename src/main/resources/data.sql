-- 데이터베이스 생성
CREATE DATABASE IF NOT EXISTS blog_example;

-- 사용할 데이터베이스 선택
USE blog_example;

DROP TABLE IF EXISTS employee_detail;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS post_history;
DROP TABLE IF EXISTS post;
DROP TABLE IF EXISTS common_code;
DROP TABLE IF EXISTS child;
DROP TABLE IF EXISTS parent;


CREATE TABLE common_code
(
    code        VARCHAR(255) PRIMARY KEY,
    description VARCHAR(255) NOT NULL
);

CREATE TABLE employee
(
    nick_name VARCHAR(10),
    name      VARCHAR(10),
    PRIMARY KEY (nick_name, name)
);

CREATE TABLE employee_detail
(
    employee_detail_seq INT AUTO_INCREMENT,
    nick_name           VARCHAR(10),
    name                VARCHAR(10),
    content             VARCHAR(255),
    PRIMARY KEY (employee_detail_seq, nick_name, name),
    FOREIGN KEY (nick_name, name) REFERENCES employee (nick_name, name)
);

CREATE TABLE post
(
    post_no          BIGINT,
    title            VARCHAR(255),
    content          TEXT,
    created_at       DATETIME,
    category_code    VARCHAR(255),
    delete_type      CHAR(1),
    expenditure_code VARCHAR(255),
    industry_code    VARCHAR(255),
    PRIMARY KEY (post_no)
);
-- Parent 테이블 생성

CREATE TABLE parent
(
    id   BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255)
);

-- Child 테이블 생성

CREATE TABLE child
(
    id        BIGINT PRIMARY KEY AUTO_INCREMENT,
    parent_id BIGINT,
    FOREIGN KEY (parent_id) REFERENCES parent (id)
);


# CREATE TABLE post (
#                       post_no BIGINT,
#                       some_code VARCHAR(255),
#                       title VARCHAR(255),
#                       content TEXT,
#                       created_at DATETIME,
#                       category_code VARCHAR(255),
#                       delete_type CHAR(1),
#                       expenditure_code VARCHAR(255),
#                       industry_code VARCHAR(255),
#                       PRIMARY KEY (post_no, some_code)
# );

CREATE TABLE post_history
(
    post_history_no BIGINT AUTO_INCREMENT,
    post_no         BIGINT NOT NULL,
    content         TEXT,
    created_at      DATETIME,
    PRIMARY KEY (post_history_no, post_no),
    FOREIGN KEY (post_no) REFERENCES post (post_no)
);

# CREATE TABLE post_history (
#                          post_history_no BIGINT AUTO_INCREMENT,
#                          post_no BIGINT NOT NULL,
#                          some_code VARCHAR(255),
#                          content TEXT,
#                          created_at DATETIME,
#                          PRIMARY KEY (post_history_no, post_no, some_code),
#                          FOREIGN KEY (post_no, some_code) REFERENCES post(post_no, some_code)
# );


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

# select c1_0.id, p1_0.id, p1_0.name
# from child c1_0
#          left join parent p1_0 on p1_0.id = c1_0.parent_id
# where c1_0.id = ?

