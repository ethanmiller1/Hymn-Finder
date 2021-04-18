import { AfterViewInit, Component, ElementRef, Input, OnDestroy, ViewChild } from '@angular/core';
import { videoJs } from './videojs';
import {VideoJsOptions} from '../../model/videojs-options';

@Component({
  selector: 'app-video-player',
  templateUrl: './video-player.component.html',
  styleUrls: ['./video-player.component.scss']
})
export class VideoPlayerComponent implements OnDestroy, AfterViewInit {
  @ViewChild('target', { static: true })
  target!: ElementRef;
  @Input() options: VideoJsOptions = {};
  @Input() source: string;
  player: any;

  constructor() { }

  ngAfterViewInit(): void {
    this.options.sources[0].src = this.source;
    this.player = videoJs(this.target.nativeElement, this.options, this.onPlayerReady.bind(this));
  }

  onPlayerReady() {
    console.log('Player is ready.');
  }

  ngOnDestroy(): void {
    if (this.player) {
      this.player.dispose();
    }
  }

}
