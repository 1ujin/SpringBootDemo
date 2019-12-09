DROP PROCEDURE IF EXISTS proc;
DELIMITER $$
CREATE PROCEDURE proc()
BEGIN
	SELECT count(*) into @cnt FROM user;
	IF @cnt = 0 THEN
		INSERT INTO user(id, name, age) VALUES (1, 'Tom', 20), (2, 'Jack', 24);
	END IF;
END
$$
DELIMITER ;

DROP TABLE IF EXISTS user;

CREATE TABLE user (
  id DOUBLE NOT NULL PRIMARY KEY,
  name VARCHAR(20),
  age INTEGER
);

INSERT INTO user(id, name, age) VALUES (1, 'Tom', 20), (2, 'Jack', 24);

call proc;

DROP PROCEDURE proc;
