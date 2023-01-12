import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import baseUrl  from '../../../helper';

@Injectable({
  providedIn: 'root'
})
export class LoanService {
  
  constructor(private _http: HttpClient) { }

  
  public filterLoan(queryParams: HttpParams) {
    return this._http.get<any>(`${baseUrl}/loans/filterLoan/`, { params: queryParams });
  }

  public getAllLoans() {
    return this._http.get(`${baseUrl}/loans/`);
  }

  public getLoanById(loanId: any) {
    return this._http.get(`${baseUrl}/loans/${loanId}`);
  }

  public getAllBanks() {
    return this._http.get(`${baseUrl}/banks/`);
  }

  public getAllLoanTypes() {
    return this._http.get(`${baseUrl}/loans/loanTypes/`);
  }
  // add loan
  public addNewLoan(addLoan: any) {
    return this._http.post(`${baseUrl}/loans/`, addLoan);
  }

  // delete loan
  public deleteLoan(loanId: string): Observable<any> {
    return this._http.delete(`${baseUrl}/loans/${loanId}`, { responseType: 'text' });
  }

  // get emi by loan id
  public getEmiByLoanId(loanId: any) {
    return this._http.get(`${baseUrl}/emi/category/${loanId}`);
  }

  updateEmi(addEmi: { loanNo: string; emiAmount: string; emiDate: string; emiStatus: boolean; }) {
    return this._http.post(`${baseUrl}/emi/`, addEmi);
  }

  getAllActiveLoans() {
    return this._http.get(`${baseUrl}/loans/active/`);
  }

}
