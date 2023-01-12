import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import baseUrl  from '../../../helper';

@Injectable({
  providedIn: 'root'
})
export class JobMasterService {

  constructor(private _http: HttpClient) { }


  // add Job
  public addJob(addJob: any) {
    return this._http.post(`${baseUrl}/jobs/`, addJob);
  }


  // Delete policy
  public deleteJob(jobId: string): Observable<any> {
    return this._http.delete(`${baseUrl}/jobs/${jobId}`, { responseType: 'text' });
  }

  // Get all bank list
  public getAllJobs() {
    return this._http.get(`${baseUrl}/jobs/`);
  }

  // Search Jobs
  public searchJobs(queryParams: HttpParams) {
    return this._http.get<any>(`${baseUrl}/jobs/filterJob`, { params: queryParams });
  }


}