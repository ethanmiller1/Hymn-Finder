import {Component, OnInit} from '@angular/core';
import {Sermon} from "../../model/sermon";
import {SermonService} from "../../services/sermon.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-video-page',
  templateUrl: './video-page.component.html',
  styleUrls: ['./video-page.component.scss']
})
export class VideoPageComponent implements OnInit {

  // @ts-ignore
  sermon: Sermon;

  constructor(private sermonService: SermonService,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(() => {
      this.handleVideoPage();
    })
  }

  handleVideoPage() {

    // @ts-ignore
    const sermonId: number = +this.route.snapshot.paramMap.get('id');

    this.sermonService.getSermon(sermonId).subscribe(
      data => {
        this.sermon = data;
      }
    )

  }
}


