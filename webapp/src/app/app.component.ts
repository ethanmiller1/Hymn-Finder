import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'angular-unbound-preaching';
  bgAudio = new Audio();
  hoverSharp = new Audio();

  constructor() { }

  ngOnInit(): void {
    this.hoverSharp.src = "../assets/audio/hover__sharp.mp3";
    this.hoverSharp.load();
    this.playAudio();
  }

  playAudio(){
    this.bgAudio.src = "../assets/audio/background.mp3";
    this.bgAudio.load();
    this.bgAudio.play();
  }

  toggleAudio(){
    this.bgAudio.paused ? this.bgAudio.play() : this.bgAudio.pause();
  }

  playHoverSound() {
    this.hoverSharp.play();
  }
}
