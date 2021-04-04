import {Component} from '@angular/core';
import {AudioBar} from "./model/audio-bar";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'angular-unbound-preaching';
  bgAudio = new Audio('../assets/audio/background.mp3');
  hoverSharp = new Audio();
  stopId: number;
  audioBars: AudioBar[];

  constructor() {
    this.stopId = 1;
    this.audioBars = [];
    this.bgAudio.loop = true;
  }

  ngOnInit(): void {
    (document.querySelectorAll('.audio__bar') as NodeListOf<HTMLElement>).forEach((element) => {
      this.audioBars.push(new AudioBar(element, 0.2, 1));
    });
    this.hoverSharp.src = "../assets/audio/hover__sharp.mp3";
    this.hoverSharp.load();
    this.bgAudio.load();
  }

  toggleAudio() {
    this.bgAudio.paused ? this.bgAudio.play() : this.bgAudio.pause();
    this.toggleAnimation();
  }

  toggleAnimation() {
    if (!this.bgAudio.paused) {
      this.animateBars();
    } else {
      cancelAnimationFrame(this.stopId);
      this.audioBars.forEach((bar) => bar.shrink());
    }
  }

  playHoverSound() {
    this.hoverSharp.play();
  }

  animateBars() {
    this.audioBars[0].ready = true;
    this.audioBars.forEach((bar) => bar.grow())

    for (let i = 0; i < 3; i++) {
      if (this.audioBars[i].currentScale > .7) {
        this.audioBars[i + 1].ready = true;
      }
    }

    let i = 0;
    while(!this.audioBars[3].ready && i < 3) {
      if (this.audioBars[i].currentScale > .7) {
        this.audioBars[i + 1].ready = true;
      }
      i++;
    }

    this.stopId = window.requestAnimationFrame(() => this.animateBars());
  }
}
