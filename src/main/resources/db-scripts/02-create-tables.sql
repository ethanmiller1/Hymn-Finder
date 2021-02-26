-- -----------------------------------------------------
-- Schema unbound
-- -----------------------------------------------------
drop schema if exists unbound;

create schema unbound;

use unbound;

-- -----------------------------------------------------
-- Table unbound.youtubeinfo
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Table unbound.sermon
-- -----------------------------------------------------
create table if not exists sermon (
    id int not null auto_increment primary key,
    title varchar (128),
    preacher varchar (48),
    mp3 varchar (64),
    video_id varchar (12),
    you_tube_title varchar (160),
    channel_title varchar (64),
    description varchar (255),
    date varchar (24)
);