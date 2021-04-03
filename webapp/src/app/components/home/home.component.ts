import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  hoverSharp = new Audio();

  constructor() {
  }

  ngOnInit(): void {
    this.hoverSharp.src = "../assets/audio/hover_tink.mp3";
  }

  playHoverSound() {
    this.hoverSharp.play();

  }

}
