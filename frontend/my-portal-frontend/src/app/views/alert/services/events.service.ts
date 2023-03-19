import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from 'src/app/helper';

@Injectable({
  providedIn: 'root'
})
export class EventsService {

  constructor(private _http: HttpClient) { }

  public getAllEvents() {
    return this._http.get(`${baseUrl}/events/`);
  }
}
