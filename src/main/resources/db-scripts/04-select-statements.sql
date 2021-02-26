use sermondb;

select *
from sermon;

select *
from youtubeinfo;

select s.Title, s.Preacher, y.VideoId, y.ChannelTitle, s.Date, s.Mp3
from sermon as s
inner join youtubeinfo as y
on s.YouTubeInfoID = y.Id

use sermondb;
select s.Title, s.Preacher, s.Mp3, y.VideoId, y.ChannelTitle, y.Description, y.Title As YouTubeTitle, s.Date
from sermon as s
inner join youtubeinfo as y
on s.YouTubeInfoID = y.Id
limit 10

select *
from youtubeinfo
where id = 74;

select s.Title, s.Preacher, y.VideoId, y.ChannelTitle, s.Date, s.Mp3, y.Title, s.Id
from sermon as s
inner join youtubeinfo as y
on s.YouTubeInfoID = y.Id
where y.Title like "%Terry%"