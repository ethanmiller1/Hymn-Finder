export class YouTubeInfo {
   videoId: string;
   channelTitle: string;
   description: string;
   title: string;


  constructor(videoId: string, channelTitle: string, description: string, title: string) {
    this.videoId = videoId;
    this.channelTitle = channelTitle;
    this.description = description;
    this.title = title;
  }
}
