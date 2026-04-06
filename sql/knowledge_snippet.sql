
CREATE TABLE IF NOT EXISTS sys_knowledge_category (
  category_id bigint(20) NOT NULL AUTO_INCREMENT,
  category_name varchar(100) NOT NULL,
  parent_id bigint(20) DEFAULT 0,
  order_num int(4) DEFAULT 0,
  status char(1) DEFAULT '0',
  create_by varchar(64) DEFAULT '',
  create_time datetime DEFAULT NULL,
  update_by varchar(64) DEFAULT '',
  update_time datetime DEFAULT NULL,
  remark varchar(500) DEFAULT NULL,
  PRIMARY KEY (category_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS sys_knowledge_snippet (
  snippet_id bigint(20) NOT NULL AUTO_INCREMENT,
  category_id bigint(20) NOT NULL,
  snippet_title varchar(200) NOT NULL,
  snippet_body longtext,
  visibility char(1) DEFAULT '0',
  view_count int(11) DEFAULT 0,
  status char(1) DEFAULT '0',
  create_by varchar(64) DEFAULT '',
  create_time datetime DEFAULT NULL,
  update_by varchar(64) DEFAULT '',
  update_time datetime DEFAULT NULL,
  remark varchar(500) DEFAULT NULL,
  PRIMARY KEY (snippet_id),
  KEY idx_snippet_category (category_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
