insert into b_user (create_time, update_time, username, password, role) values (now(), now(), '小明1', '123456', 1);
insert into b_user (create_time, update_time, username, password, role) values (now(), now(), '小明2', '123456', 1);
insert into b_user (create_time, update_time, username, password, role) values (now(), now(), '小明3', '123456', 1);
insert into b_user (create_time, update_time, username, password, role) values (now(), now(), '小明4', '123456', 1);

insert into b_article (create_time, update_time, content, title, user_id) values (now(), now(), '文章内容123', '文章标题1', 1L);
insert into b_article (create_time, update_time, content, title, user_id) values (now(), now(), '文章内容123', '文章标题2', 1L);
insert into b_article (create_time, update_time, content, title, user_id) values (now(), now(), '文章内容123', '文章标题3', 1L);
insert into b_article (create_time, update_time, content, title, user_id) values (now(), now(), '文章内容123', '文章标题4', 1L);

insert into b_comment (create_time, update_time, article_id, content) values (now(), now(), 1, '评论内容1');
insert into b_comment (create_time, update_time, article_id, content) values (now(), now(), 1, '评论内容2');
insert into b_comment (create_time, update_time, article_id, content) values (now(), now(), 1, '评论内容3');

insert into b_reply (create_time, update_time, comment_id, content) values (now(), now(), 1L, '回复内容1');
