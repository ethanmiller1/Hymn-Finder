use sermondb;

create table youtubeinfo (
    Id int not null auto_increment primary key,
    Link varchar(255),
    VideoId varchar(255),
    ChannelTitle varchar(255),
    Description varchar(255),
    Title varchar(255)
);

create table sermon (
    Id int not null auto_increment primary key,
    YouTubeInfoID int,
    Date varchar(45),
    Title varchar(255),
    Mp3 varchar(255),
    Preacher varchar(255),
    foreign key (YouTubeInfoID)
        references youtubeinfo(Id)
        on update restrict
        on delete restrict
);