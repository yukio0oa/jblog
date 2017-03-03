
insert into users values ('rhgpdud95', 'aa', 'aa', sysdate);


select * from users;
select * from blog;
select * from category;

commit;

delete from users where user_id='aa';


select user_id as userId, name
		  from users 
		 where user_id = 'aa';
		 
select user_id as userId, name from users where user_id = 'ddddd' and password = '1234';

insert all into users values ('rhgpdud95', '고혜영', '1234', sysdate)
		   into blog values ('rhgpdud95')
		   into category values ('rhgpdud95')
	select user_id, name, password, reg_date
		from users
	where user_id = 'rhgpdud95';

insert all into users values (user_id, name, password, reg_date)
		   into blog values (user_id)
		   into category values (user_id)
	select user_id, name, password, reg_date
		from users
	where user_id = 'rhgpdud95';


insert all into users values (user_id, name, password, reg_date)
	   into blog values (user_id, title, logo)
	   into category values (cno, cname, description, reg_date, user_id)
select user_id, name, password, reg_date
	from users
where user_id = 'rhgpdud95';
