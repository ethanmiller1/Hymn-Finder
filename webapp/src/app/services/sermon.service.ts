import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from 'rxjs';
import {Sermon} from '../common/sermon';
import {map} from 'rxjs/operators';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SermonService {

  private baseUrl = environment.baseUrl + 'api/sermons';

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
  };
}
