insert into b_user (create_time, update_time, username, password, role) values (now(), now(), '小明', '123456', 1);
insert into b_article (create_time, update_time, content, title) values (now(), now(), '文章内容123', '文章标题1');
insert into b_article (create_time, update_time, content, title) values (now(), now(), '文章内容123', '文章标题2');
insert into b_article (create_time, update_time, content, title) values (now(), now(), '文章内容123', '文章标题3');
insert into b_article (create_time, update_time, content, title) values (now(), now(), '文章内容123', '文章标题4');
insert into b_comment (create_time, update_time, from_user_id, article_id, content) values (now(), now(), '1', '1', '评论内容123');
insert into b_reply (create_time, update_time, comment_id, from_user_id, content) values (now(), now(), '1', '1', '评论内容123');
