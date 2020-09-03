insert into t_user (create_time, update_time, username, password, role) values (now(), now(), "小明", "123456", 1);
insert into t_article (create_time, update_time, content) values (now(), now(), "文章内容123",);
insert into t_comment (create_time, update_time, from_user_id, article_id, content) values (now(), now(), "1", "1", "评论内容123");
insert into t_reply (create_time, update_time, comment_id, from_user_id, content) values (now(), now(), "1", "1", "评论内容123");
