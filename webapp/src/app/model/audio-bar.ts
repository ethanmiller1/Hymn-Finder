export class AudioBar {
  singleBar: HTMLElement;
  currentScale: number;
  scaleDirection: number;
  ready = false;


  constructor(singleBar: HTMLElement, currentScale: number, scaleDirection: number) {
    this.singleBar = singleBar;
    this.currentScale = currentScale;
    this.scaleDirection = scaleDirection;
  }

  grow() {
    if (this.ready) {
      if (this.currentScale > 1 || this.currentScale < 0.2) {
        this.scaleDirection = this.scaleDirection * -1;
      }

      this.currentScale += .065 * this.scaleDirection;
      this.singleBar.style.transform = `translate3d(6px, -6px, 0px) scaleY(${this.currentScale})`;
    }
  }

  shrink() {
    this.currentScale = .2;
    this.singleBar.style.transform = `translate3d(6px, -6px, 0px) scaleY(${this.currentScale})`;
    this.ready = false;
  }

}
