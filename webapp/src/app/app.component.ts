import {Component} from '@angular/core';
import {AudioBar} from "./model/audio-bar";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'angular-unbound-preaching';
  bgAudio = new Audio();
  hoverSharp = new Audio();
  toggle = true;
  currentScale = 0.2143;
  scaleDirection = 1;
  stopIds = new Array(0);
  audioBars: AudioBar[];

  constructor() {
    this.audioBars = [];
  }

  ngOnInit(): void {
    (document.querySelectorAll('.audio__bar') as NodeListOf<HTMLElement>).forEach((element) => {
      this.audioBars.push(new AudioBar(element, 0.2, 1));
    });
    this.hoverSharp.src = "../assets/audio/hover__sharp.mp3";
    this.hoverSharp.load();
    this.playAudio();
  }

  playAudio() {
    this.bgAudio.src = "../assets/audio/background.mp3";
    this.bgAudio.load();
    this.bgAudio.play();
    this.animateBars();
  }

  toggleAudio() {
    this.bgAudio.paused ? this.bgAudio.play() : this.bgAudio.pause();
    this.toggleAnimation();
  }

  toggleAnimation() {
    if (!this.toggle) {
      this.toggle = true;
      this.animateBars();
    } else {
      this.toggle = false;
      this.audioBars.forEach((bar) => {
        cancelAnimationFrame(bar.stopId);
        bar.singleBar.style.transform = 'translate3d(6px, -6px, 0px) scaleY(0.2)';
      })
    }
  }

  playHoverSound() {
    this.hoverSharp.play();
  }

  animateBars() {
    this.audioBars.forEach((bar) => {
      setTimeout(() => {
        this.stopIds.push(this.animateBar(bar));
      }, 500);
    });
  }

  animateBar(bar: AudioBar) {
    bar.currentScale += .065 * bar.scaleDirection;
    bar.singleBar.style.transform = `translate3d(6px, -6px, 0px) scaleY(${bar.currentScale})`;

    if (bar.currentScale > 1 || bar.currentScale <= 0.2143) {
      bar.scaleDirection = bar.scaleDirection * -1;
    }
    bar.stopId = window.requestAnimationFrame(() => this.animateBar(bar));
  }
}
