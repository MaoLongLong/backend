-- auto Generated on 2020-12-26
DROP TABLE IF EXISTS classroom;
CREATE TABLE classroom
(
    id          INT(11)     NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name`      VARCHAR(50) NOT NULL COMMENT 'name',
    capacity    INT(11)     NOT NULL COMMENT 'capacity',
    building_id INT(11)     NOT NULL COMMENT 'buildingId',
    `status`    TINYINT(3) DEFAULT 0 COMMENT 'status',
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT 'classroom';
