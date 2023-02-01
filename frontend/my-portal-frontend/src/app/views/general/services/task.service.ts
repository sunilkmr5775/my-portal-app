import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import baseUrl  from '../../../helper';

@Injectable({
  providedIn: 'root'
})
export class TaskService {
  
  constructor(private _http: HttpClient) { }

  
  public filterTask(queryParams: HttpParams) {
    return this._http.get<any>(`${baseUrl}/tasks/filterTask/`, { params: queryParams });
  }

  public getAllTasks() {
    return this._http.get(`${baseUrl}/tasks/`);
  }

  public getTaskById(taskId: any) {
    return this._http.get(`${baseUrl}/tasks/${taskId}`);
  }

 
  // add loan
  public addNewTask(addLoan: any) {
    return this._http.post(`${baseUrl}/tasks/`, addLoan);
  }

  // delete task
  public deleteTask(taskId: string): Observable<any> {
    return this._http.put(`${baseUrl}/tasks/delete/${taskId}`, { responseType: 'text' });
  }

   // Mark Task As Done
   public markAsDone(taskId: string): Observable<any> {
    return this._http.put(`${baseUrl}/tasks/${taskId}`, { responseType: 'text' });
  }

  // Mark Task As Undone
  public markAsUndone(taskId: string): Observable<any> {
    return this._http.put(`${baseUrl}/tasks/undone/${taskId}`, { responseType: 'text' });
  }

public fetchAllActiveTasks() {
    return this._http.get(`${baseUrl}/tasks/pending-task/`);
  }

}
