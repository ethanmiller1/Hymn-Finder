import {Component, OnInit, ViewChild} from '@angular/core';
import {SermonService} from '../../services/sermon.service';
import {Sermon} from '../../model/sermon';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';

@Component({
  selector: 'app-sermon-list',
  templateUrl: './sermon-list.component.html',
  styleUrls: ['./sermon-list.component.scss']
})
export class SermonListComponent implements OnInit {

  @ViewChild(MatSort) sort: MatSort;
  displayedColumns: string[] = ['title', 'preacher', 'church', 'datetime'];
  // sermons: Sermon[] = [];
  sermons: Sermon[] = [
    { id: "1", datetime: "8/22/2021", title: "The Rapture in Thessalonians", mp3: "", preacher: "Pastor Anderson", youTubeInfo: null, archiveResource: ""},
    { id: "2", datetime: "8/22/2021", title: "The Evening and the Morning", mp3: "", preacher: "Pastor Anderson", youTubeInfo: null, archiveResource: ""}
  ];
  dataSource;

  constructor(private sermonService: SermonService) { }

  ngOnInit(): void {
    this.listSermons();
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }


  listSermons() {
    this.sermonService.getSermonList()
      .subscribe(
        data => {
          console.log(data);
          this.sermons = data;
          this.dataSource = new MatTableDataSource(this.sermons);
          this.dataSource.sort = this.sort;
        }
      );
    console.log("after" + this.sermons);
  }

}
