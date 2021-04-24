import {YouTubeInfo} from './youtubeinfo';

export class Sermon {
  id: string;
  datetime: string;
  title: string;
  mp3: string;
  preacher: string;
  youTubeInfo: YouTubeInfo;
  archiveResource: string;

  constructor(id: string, datetime: string, title: string, mp3: string, preacher: string, youTubeInfo: YouTubeInfo, archiveResource: string) {
    this.id = id;
    this.datetime = datetime;
    this.title = title;
    this.mp3 = mp3;
    this.preacher = preacher;
    this.youTubeInfo = youTubeInfo;
    this.archiveResource = archiveResource;
  }

}
