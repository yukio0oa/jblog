
insert into users values ('rhgpdud95', 'aa', 'aa', sysdate);


select * from users;
select * from blog;
select * from category;
select * from post;

commit;
rollback;

delete from users where password='1234';
delete from blog where blog_id='rhgpdud95';
delete from category where cno=2;



select user_id as userId, name
		  from users 
		 where user_id = 'aa';
		 
select user_id as userId, name from users where user_id = 'ddddd' and password = '1234';

insert into blog values ('rhgpdud95', 'title', 'logo');

update blog set title= '', logo='201712752437937.jpg' where blog_id='rhgpdud95'; 

select	 cno, 
		 cname,
		 description,
		 to_char(reg_date, 'yyyy-mm-dd') as regDate 
		    from category
		order by cno desc;
		
insert into category values (seq_category.nextval, 'name', 'content', sysdate, 'rhgpdud95');

select max(pno),
	   p.ptitle,
	   p.content,
	   p.reg_date,
	   c.blog_id,
	   c.cno
	from category c, post p
where c.BLOG_ID='rhgpdud95' and c.cno=p.CNO;

select max(pno), c.BLOG_ID
	from post p, category c
where p.cno = c.cno
group by blog_id;




