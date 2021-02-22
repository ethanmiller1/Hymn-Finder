use sermondb;

drop table sermon;
drop table youtubeinfo;

delete from sermon
where YouTubeInfoID is null