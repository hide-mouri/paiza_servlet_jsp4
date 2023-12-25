CREATE SCHEMA `mylog` ;

CREATE TABLE reviews (
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(255),
  content TEXT
);

INSERT INTO reviews(title,content)
  VALUES
    ("hello world","一番目のコメントです。"),
    ("hello Servlet","二番目のコメントです。"),
    ("hello JSP","三番目のコメントです。"),
    ("hello paiza","四番目のコメントです。"),
    ("こんにちは","五番目のコメントです。");
