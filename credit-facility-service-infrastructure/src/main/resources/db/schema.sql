CREATE TABLE `t_product_limit`
(
    `id`              bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `create_time`     datetime default now(),
    `edit_time`       datetime default now(),
    `deleted`         tinyint             NOT NULL DEFAULT 0 COMMENT '逻辑删除',
    `version`         bigint(20) default 0,
    `serial_number`   varchar(64),
    `account_id`         bigint(20) comment '额度账户',
    `customer_id`     bigint(20),
    `user_id`         bigint(20),
    `product_code`    varchar(64),
    `quota_limit`     bigint(20) default 0,
    `quota_balance`   bigint(20) default 0,
    `quota_occupancy` bigint(20) default 0,
    `quota_frozen`    bigint(20) default 0,
    `quota_base`      bigint(20) default 0,
    `quota_change`    bigint(20) default 0,
    `quota_mode`      tinyint,
    `frozen_status`   tinyint default 1,
    `frozen_time`     datetime COMMENT '冻结时间',
    `expire_status`   tinyint,
    `expiration_time` datetime default now()+300 COMMENT '过期时间',
    `active_status`   tinyint,
    `inactive_time`   datetime COMMENT '禁用时间',
    `parent_id`       bigint(20),
    `abandoned`       tinyint default 0,
    PRIMARY KEY (`id`)
);

CREATE TABLE `t_customer_limit`
(
    `id`              bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `create_time`     datetime default now(),
    `edit_time`       datetime default now(),
    `deleted`         tinyint             NOT NULL DEFAULT 0 COMMENT '逻辑删除',
    `version`         bigint(20) default 0,
    `serial_number`   varchar(64),
    `account_id`         bigint(20),
    `customer_id`     bigint(20),
    `user_id`         bigint(20),
    `product_code`    varchar(64),
    `quota_limit`     bigint(20) default 0,
    `quota_balance`   bigint(20) default 0,
    `quota_occupancy` bigint(20) default 0,
    `quota_frozen`    bigint(20) default 1,
    `quota_base`      bigint(20) default 0,
    `quota_change`    bigint(20) default 0,
    `quota_mode`      tinyint,
    `quota_type`      varchar(10),
    `frozen_status`   tinyint default 1,
    `frozen_time`     datetime COMMENT '冻结时间',
    `expire_status`   tinyint,
    `expiration_time` datetime default now() COMMENT '过期时间',
    `active_status`   tinyint,
    `inactive_time`   datetime COMMENT '禁用时间',
    `parent_id`       bigint(20),
    `abandoned`       tinyint default 0,
    PRIMARY KEY (`id`)
);