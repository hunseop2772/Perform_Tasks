use aidev;
select * from tb_member;
alter table tb_member add mem_gender varchar(20);
alter table tb_member add mem_userpw varchar(256);
alter table tb_member drop mem_userpw ;
desc tb_member;

#SHA2
# 대표적인 단방향 암호화 해시함수
select sha2('1234', 256);
select sha2('angel1004!))$', 256);

select hex(aes_encrypt(sha2('1234',256), '111'));

# tb_member의 비밀번호 저장 크기를 변경a
# 회원가입시 비밀번호를 sha2를 이용하여 저장
# 로그인시 비밀번호를 sha2로 체트하여 로그인

#insert into 테이블명 (필드..) values(?,?,sha2(?,256),?,..)
#서블릿으로 변환해서 작업 함 