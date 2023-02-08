# Alicia #
#### 艾莉西亚 ####

---

This is an Alicia forum's server.

The config file is __/Resource/config.properties__

Database Table code (MYSQL) :
````mysql
CREATE TABLE user_info (
	id INT PRIMARY KEY AUTO_INCREMENT,
	profile VARCHAR(256) NOT NULL,
	sign VARCHAR(256) DEFAULT '这个人很懒，没有签名！',
	log_up_time DATETIME NOT NULL
);

CREATE TABLE user (
	id INT PRIMARY KEY AUTO_INCREMENT,
	account VARCHAR(256) NOT NULL UNIQUE,
	password VARCHAR(256) NOT NULL,
	name VARCHAR(256) NOT NULL,
	info_id INT NOT NULL UNIQUE,
	CONSTRAINT fk_user_info_id FOREIGN KEY(info_id) REFERENCES user_info(id)
);

CREATE TABLE article (
	id INT PRIMARY KEY AUTO_INCREMENT,
	user_id INT NOT NULL,
	value MEDIUMTEXT NOT NULL,
	time DATETIME NOT NULL,
	CONSTRAINT fk_article_user_id FOREIGN KEY(user_id) REFERENCES user(id)
);

CREATE TABLE comment (
	id INT PRIMARY KEY AUTO_INCREMENT,
	user_id INT NOT NULL ,
	article_id INT NOT NULL,
	value MEDIUMTEXT NOT NULL,
	time DATETIME NOT NULL,
	CONSTRAINT fk_comment_user_id FOREIGN KEY(user_id) REFERENCES user(id),
	CONSTRAINT fk_comment_article_id FOREIGN KEY(article_id) REFERENCES article(id)
);

CREATE TABLE token (
	id INT PRIMARY KEY AUTO_INCREMENT,
	value VARCHAR(255) NOT NULL UNIQUE,
	create_time DATETIME NOT NULL,
	expire_time DATETIME NOT NULL,
	user_id INT NOT NULL,
	CONSTRAINT fk_token_user_id FOREIGN KEY(user_id) REFERENCES user(id)
);
````