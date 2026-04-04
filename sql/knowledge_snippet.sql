
CREATE TABLE IF NOT EXISTS sys_knowledge_category (
  category_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '分类ID',
  category_name varchar(100) NOT NULL COMMENT '分类名称',
  parent_id bigint(20) DEFAULT 0 COMMENT '父分类ID',
  order_num int(4) DEFAULT 0 COMMENT '排序',
  status char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  create_by varchar(64) DEFAULT '' COMMENT '创建者',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_by varchar(64) DEFAULT '' COMMENT '更新者',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  remark varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (category_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='知识库分类';

CREATE TABLE IF NOT EXISTS sys_knowledge_snippet (
  snippet_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '片段ID',
  category_id bigint(20) NOT NULL COMMENT '分类ID',
  snippet_title varchar(200) NOT NULL COMMENT '标题',
  snippet_body longtext COMMENT '正文',
  visibility char(1) DEFAULT '0' COMMENT '可见性（0部门 1私有 2公开）',
  view_count int(11) DEFAULT 0 COMMENT '浏览次数',
  status char(1) DEFAULT '0' COMMENT '状态（0草稿 1发布 2归档）',
  create_by varchar(64) DEFAULT '' COMMENT '创建者',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_by varchar(64) DEFAULT '' COMMENT '更新者',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  remark varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (snippet_id),
  KEY idx_snippet_category (category_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='知识库片段';
