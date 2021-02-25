import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import {Observable} from "rxjs";
import {Sermon} from "../common/sermon";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class SermonService {

  private baseUrl = 'http://localhost:8081/api/sermons';

  constructor(private httpClient: HttpClient) { }

  getSermonList(): Observable<Sermon[]> {
    return this.httpClient.get<GetResponse>(this.baseUrl).pipe(
      map( response => response._embedded.sermons )
    );
  }
}

interface GetResponse {
  _embedded: {
    sermons: Sermon[];
  }
}
