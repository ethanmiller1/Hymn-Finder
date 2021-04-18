import {AfterViewInit, Component, Input, OnInit} from '@angular/core';
import {Sermon} from "../../model/sermon";

@Component({
  selector: 'app-audio-player',
  templateUrl: './audio-player.component.html',
  styleUrls: ['./audio-player.component.scss']
})
export class AudioPlayerComponent implements AfterViewInit, OnInit {

  // @ts-ignore
  audio: Audio;
  @Input() sermon: Sermon;

  constructor() { }

  ngOnInit(): void {
  }

  ngAfterViewInit() {
    this.audio = new Audio(this.sermon.mp3);
    this.audio.load();
  }

  togglePlay() {
    this.audio.paused ? this.audio.play() : this.audio.pause();
  }

}
