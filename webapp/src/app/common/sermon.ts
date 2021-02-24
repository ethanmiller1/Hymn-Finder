export class Sermon {
  id: string;
  date: string;
  title: string;
  mp3: string;
  preacher: string;
  youTubeInfo: string;

  constructor(id: string, date: string, title: string, mp3: string, preacher: string, youTubeInfo: string) {
    this.id = id;
    this.date = date;
    this.title = title;
    this.mp3 = mp3;
    this.preacher = preacher;
    this.youTubeInfo = youTubeInfo;
  }

}
