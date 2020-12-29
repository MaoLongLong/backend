-- auto Generated on 2020-12-28
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    id         INT(11)      NOT NULL AUTO_INCREMENT COMMENT 'id',
    username   VARCHAR(50)  NOT NULL COMMENT 'username',
    `password` VARCHAR(100) NOT NULL COMMENT 'password',
    avatar     VARCHAR(100) NOT NULL COMMENT 'avatar',
    nickname   VARCHAR(50)  NOT NULL COMMENT 'nickname',
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT 'user';
