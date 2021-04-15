import {AfterViewInit, Component, EventEmitter, ElementRef, Input, OnInit, Output, ViewChild} from '@angular/core';
import {Sermon} from "../../model/sermon";
import {SermonService} from "../../services/sermon.service";
import {ActivatedRoute} from "@angular/router";
import {VideoJsOptions} from "../../model/videojs-options";

@Component({
  selector: 'app-video-page',
  templateUrl: './video-page.component.html',
  styleUrls: ['./video-page.component.scss']
})
export class VideoPageComponent implements AfterViewInit, OnInit {

  // @ts-ignore
  audio: Audio;
  sermon: Sermon;

  @ViewChild('video', {static: false}) private video: ElementRef;
  @ViewChild('playCover', {static: false}) private playCover: ElementRef;

  playing = false;
  playbackHover: boolean;rea
  volumeHover: boolean;
  videoJsOptions: VideoJsOptions = {
    controls: true,
    loadingSpinner: true,
    sources: [
      {
        src: 'https://ia903109.us.archive.org/20/items/CompleteYouTubeChannelPart11of11/Pastor%20Anderson%27s%20Complete%20Channel%20Part%2011%20of%2011/045%20%20%27Let%20Him%20Be%20Your%20Dread%27%20%28Baptist%20Preaching%29-9vGdyAddAV0.mp4',
        type: 'video/mp4'
      },
    ],
    plugins: {
      seekButtons: {
        forward: 10,
        back: 10
      }
    },
    inactivityTimeout: 0,
    userActions: {
      doubleClick: true, // to toggle full screen on double click
      hotkeys: function (event: any) {

        // `up arrow` key = forward 10 sec
        if (event.which === 38) {
          this.volume(this.volume() + 0.2);
        }

        // `up arrow` key = forward 10 sec
        if (event.which === 40) {
          this.volume(this.volume() - 0.2);
        }

        // `right arrow` key = forward 10 sec
        if (event.which === 39) {
          this.currentTime(this.currentTime() + 10);
        }

        // `left arrow` key = backward 10 sec
        if (event.which === 37) {
          this.currentTime(this.currentTime() - 10);
        }

        // `f` key = toggle full screen
        if (event.which === 70) {
          if (!this.isFullscreen()) {
            this.enterFullWindow();
          } else {
            this.exitFullWindow();
          }
        }

        // `m` key = toggle mute
        if (event.which === 77) {
          if (this.muted()) {
            this.muted(false);
          } else {
            this.muted(true);
          }
        }

        // ` `(space) key = play/pause
        if (event.which === 32) {
          if (this.paused()) {
            this.play();
          } else {
            this.pause();
          }
        }

      }
    }
  };

  constructor(private sermonService: SermonService,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(() => {
      this.handleVideoPage();
    })
  }

  ngAfterViewInit() {
    console.log(this.video);
    this.audio = new Audio(this.sermon.mp3);
    this.audio.load();
  }

  togglePlay() {
    this.audio.paused ? this.audio.play() : this.audio.pause();
  }

  handleVideoPage() {
    const sermonId: number = +this.route.snapshot.paramMap.get('id');

    this.sermonService.getSermon(sermonId).subscribe(
      data => {
        this.sermon = data;
      }
    )
  }

  toggleVideo() {
    let video = this.video.nativeElement;
    if (video.paused) {
     video.play();
      this.playing = true;
    } else {
      video.pause();
      this.playing = false;
    }
  }

}


