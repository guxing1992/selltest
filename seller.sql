create table `seller_info`(
  `seller_id` varchar(32) not null,
  `username` varchar(32) not null,
  `password` varchar(32) not null,
  `openid` varchar(64) not null comment '微信openid',
  `create_time` timestamp not null default current_timestamp comment '创建时间',
  `update_time` timestamp not null default current_timestamp on update current_timestamp comment '更新时间',
  primary key (`seller_id`)
)comment '卖家信息表';