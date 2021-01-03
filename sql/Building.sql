-- auto Generated on 2020-12-26
DROP TABLE IF EXISTS building;
CREATE TABLE building
(
    id         INT(11)     NOT NULL AUTO_INCREMENT COMMENT 'id',
    full_name  VARCHAR(50) NOT NULL COMMENT 'fullName',
    alias_name VARCHAR(50) NOT NULL COMMENT 'aliasName',
    department VARCHAR(50) NOT NULL COMMENT 'department',
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT 'building';
