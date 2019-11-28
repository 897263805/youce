create table UPLOAD
(
	ID INTEGER auto_increment,
	fileName  VARCHAR(200),
	fileType VARCHAR(200),
	fileDownloadUri VARCHAR(200),
	size int(200),
        userId VARCHAR(200),
  uploadUri VARCHAR(200),
	GMT_CREATE BIGINT,
	GMT_MODIFIED BIGINT,
	constraint UPLOAD_PK
		primary key (ID)
);

create table USER
(
	ID INTEGER auto_increment,
	name VARCHAR(100),
	realname VARCHAR(50),
	logindate CHAR(36),
	constraint USER_PK
		primary key (ID)
);
alter table upload add uploadDate varchar(30)  
alter table user add password varchar(30)
