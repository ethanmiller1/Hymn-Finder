use sermondb;

select *
from sermon;

select *
from youtubeinfo;

select s.Title, s.Preacher, y.VideoId, y.ChannelTitle, s.Date, s.Mp3
from sermon as s
inner join youtubeinfo as y
on s.YouTubeInfoID = y.Id