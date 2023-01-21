import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from 'src/app/helper';

@Injectable({
  providedIn: 'root'
})
export class SystemInformationService {
  
  constructor(private _http: HttpClient) { }

  public getSystemInformation() {
    return this._http.get(`${baseUrl}/system-information/`);
  }

}
