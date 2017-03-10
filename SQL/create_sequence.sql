-- create sequence

-- seq_users
drop sequence seq_post;

create sequence seq_post
start with 1
increment by 1
maxvalue 9999999999;

commit;
-- seq_board
drop sequence seq_category;


create sequence seq_category
start with 1
increment by 1
maxvalue 9999999999;

-- seq_gallery
drop sequence seq_gallery;

create sequence seq_gallery
start with 1
increment by 1
maxValue 9999999999;
