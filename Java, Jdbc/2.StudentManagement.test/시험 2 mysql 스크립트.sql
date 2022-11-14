
create table t_student(
	st_no int auto_increment primary key,
    st_id int unique not null,
    st_name varchar(50)  not null,
    st_hp varchar(50) not null,
    st_email varchar(50),
    st_address varchar(50),
    st_tot int,
    st_rank int,
    st_rdate datetime default now()
);

delete from t_student;
drop database aidev;

create table t_score(
    sc_id int ,
	sc_java int ,
    sc_python int ,
    sc_c int,
    sc_rdate datetime default now(),
    sc_sum int,
    sc_avg int,
    foreign key(sc_id) references t_student(st_id)
);

create database aidev;
use aidev;
use t_student;
use t_score;


SELECT * FROM aidev.t_score;
SELECT * FROM aidev.t_student;

select count(*) as st_tot from t_student ;

-- select row_number() over(order by sc_avg desc, st_id desc) as st_rank,  count(distinct st_no) as st_tot
-- ,st_id, st_name,st_hp,st_email,st_address, sc_java,sc_python,sc_c,sc_avg,sc_sum,st_rdate  
-- from t_student left join t_score on t_student.st_id = t_score.sc_id  order by t_score.sc_avg desc;
