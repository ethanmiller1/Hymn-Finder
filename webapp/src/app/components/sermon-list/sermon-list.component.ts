import { Component, OnInit } from '@angular/core';
import {SermonService} from '../../services/sermon.service';
import {Sermon} from '../../model/sermon';

@Component({
  selector: 'app-sermon-list',
  templateUrl: './sermon-list.component.html',
  styleUrls: ['./sermon-list.component.scss']
})
export class SermonListComponent implements OnInit {

  sermons: Sermon[] = [];

  constructor(private sermonService: SermonService) { }

  ngOnInit(): void {
    this.listSermons();
  }

  listSermons() {
    this.sermonService.getSermonList()
      .subscribe(
        data => {
          this.sermons = data;
        }
      );
    console.log(this.sermons);
  }

}
