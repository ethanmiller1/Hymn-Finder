import {Component} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'angular-unbound-preaching';
  bgAudio = new Audio();
  hoverSharp = new Audio();
  currentScale = 0.2143;
  scaleDirection = 1;
  toggle = true;
  stopId: any;

  constructor() {
  }

  ngOnInit(): void {
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
      window.requestAnimationFrame(() => this.animateBars());
    } else {
      this.toggle = false;
      cancelAnimationFrame(this.stopId);
    }
  }

  playHoverSound() {
    this.hoverSharp.play();
  }

  animateBars() {
    let audioBars = document.querySelectorAll('.audio__bar') as NodeListOf<HTMLElement>;

    audioBars.forEach((bar) => {
      bar.style.transform = `translate3d(6px, -6px, 0px) scaleY(${this.currentScale})`;
      this.currentScale += .065 * this.scaleDirection;
    });

    if (this.currentScale > 1 || this.currentScale <= 0.2143) {
      this.scaleDirection = this.scaleDirection * -1;
    }

    this.stopId = window.requestAnimationFrame(() => this.animateBars());

  }
}
