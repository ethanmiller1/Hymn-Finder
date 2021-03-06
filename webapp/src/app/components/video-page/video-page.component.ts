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
export class VideoPageComponent implements OnInit {



  sermon: Sermon;
  view = View.Video;
  showDownloads = false;

  @ViewChild('video', {static: false}) private video: ElementRef;
  @ViewChild('playCover', {static: false}) private playCover: ElementRef;

  playing = false;
  playbackHover: boolean;
  volumeHover: boolean;
  videoJsOptions: VideoJsOptions = {
    controls: true,
    loadingSpinner: true,
    sources: [
      {
        src: '', // Overwritten in video-player.component.ts
        type: 'video/mp4'
      },
    ],
    plugins: {
      seekButtons: {
        forward: 15,
        back: 15
      }
    },
    playbackRates: [0.5, 1, 1.5, 2, 3],
    inactivityTimeout: 0,
    userActions: {
      doubleClick: true, // to toggle full screen on double click
      hotkeys: function (event: any) {

        /* `up arrow` key = forward 10 sec */
        if (event.which === 38) {
          this.volume(this.volume() + 0.2);
        }

        /* `up arrow` key = forward 10 sec */
        if (event.which === 40) {
          this.volume(this.volume() - 0.2);
        }

        /* `right arrow` key = forward 10 sec */
        if (event.which === 39) {
          this.currentTime(this.currentTime() + 10);
        }

        /* `left arrow` key = backward 10 sec */
        if (event.which === 37) {
          this.currentTime(this.currentTime() - 10);
        }

        /* `f` key = toggle full screen */
        if (event.which === 70) {
          if (!this.isFullscreen()) {
            this.enterFullWindow();
          } else {
            this.exitFullWindow();
          }
        }

        /* `m` key = toggle mute */
        if (event.which === 77) {
          if (this.muted()) {
            this.muted(false);
          } else {
            this.muted(true);
          }
        }

        /* ` `(space) key = play/pause */
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
    });
  }

  handleVideoPage() {
    const sermonId: number = +this.route.snapshot.paramMap.get('id');

    this.sermonService.getSermon(sermonId).subscribe(
      data => {
        this.sermon = data;
      }
    );
  }

  showVideo() {
    this.view = View.Video;
  }

  showAudio() {
    this.view = View.Audio;
  }

  showYouTube() {
    this.view = View.YouTube;
  }

  toggleDownloads() {
    this.showDownloads = !this.showDownloads;
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

  youTubeURL() {
    console.log(`https://www.youtube-nocookie.com/embed/${this.sermon.youTubeInfo.videoId}/?controls=1&enablejsapi=1&modestbranding=1&showinfo=0&origin=https%3A%2F%2Fwww.khanacademy.org&iv_load_policy=3&html5=1&autoplay=1&fs=1&rel=0&hl=en&cc_lang_pref=en&cc_load_policy=1&start=0`)
    return `https://www.youtube-nocookie.com/embed/${this.sermon.youTubeInfo.videoId}/?controls=1&enablejsapi=1&modestbranding=1&showinfo=0&origin=https%3A%2F%2Fwww.khanacademy.org&iv_load_policy=3&html5=1&autoplay=1&fs=1&rel=0&hl=en&cc_lang_pref=en&cc_load_policy=1&start=0`;
  }

}

enum View {
  Video,
  Audio,
  YouTube,
}
