import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from 'src/app/helper';

@Injectable({
  providedIn: 'root'
})
export class DatabasesService {

  constructor(private _http: HttpClient) { }

  public getDbInfo() {
    return this._http.get(`${baseUrl}/database-details/`);
  }
}
