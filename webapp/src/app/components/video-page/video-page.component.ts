import {AfterViewInit, Component, EventEmitter, ElementRef, Input, OnInit, Output, ViewChild} from '@angular/core';
import {Sermon} from "../../model/sermon";
import {SermonService} from "../../services/sermon.service";
import {ActivatedRoute} from "@angular/router";

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
  playbackHover: boolean;
  volumeHover: boolean;

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


