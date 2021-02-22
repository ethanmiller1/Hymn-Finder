-- -----------------------------------------------------
-- Schema sermondb
-- -----------------------------------------------------
drop schema if exists sermondb;

go;

create schema sermondb;

go;

use sermondb;

-- -----------------------------------------------------
-- Table sermondb.youtubeinfo
-- -----------------------------------------------------
create table if not exists youtubeinfo (
    id int not null auto_increment primary key,
    link varchar (255),
    videoid varchar (255),
    channeltitle varchar (255),
    description varchar (255),
    title varchar (255)
);

-- -----------------------------------------------------
-- Table sermondb.sermon
-- -----------------------------------------------------
create table if not exists sermon (
    id int not null auto_increment primary key,
    youtubeinfoid int,
    date varchar (45),
    title varchar (255),
    mp3 varchar (255),
    preacher varchar (255),
    foreign key (youtubeinfoid) references youtubeinfo (id) on
    update
        restrict on
    delete
        restrict
);