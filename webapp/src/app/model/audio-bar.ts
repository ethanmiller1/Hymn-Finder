import {YouTubeInfo} from "./youtubeinfo";

export class AudioBar {
  singleBar: HTMLElement;
  currentScale: number;
  scaleDirection: number;
  stopId: number;


  constructor(singleBar: HTMLElement, currentScale: number, scaleDirection: number) {
    this.singleBar = singleBar;
    this.currentScale = currentScale;
    this.scaleDirection = scaleDirection;
    this.stopId = 1;
  }


}
