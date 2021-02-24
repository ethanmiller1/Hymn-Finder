import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-sermon-list',
  templateUrl: './sermon-list.component.html',
  styleUrls: ['./sermon-list.component.css']
})
export class SermonListComponent implements OnInit {

  private baseUrl = 'http://localhost:8080/api/products';

  private categoryUrl = 'http://localhost:8080/api/product-category';

  constructor() { }

  ngOnInit(): void {
  }

}
