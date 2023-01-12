import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import baseUrl  from '../../../helper';

@Injectable({
  providedIn: 'root'
})
export class LifeInsuranceService {

  constructor(private _http: HttpClient) { }


  // Fetch all policies
  getAllPolicies() {
    // return this._http.get(`http://localhost:9023/life-insurances/active`);
    return this._http.get(`${baseUrl}/life-insurances/active`);
  }

  // add Policy
  public addNewPolicy(addPolicy: any) {
    return this._http.post(`${baseUrl}/life-insurances/`, addPolicy);
  }


  // Delete policy
  public deletePolicy(policyId: string): Observable<any> {
    return this._http.delete(`${baseUrl}/life-insurances/${policyId}`, { responseType: 'text' });
  }
  // Get  all active policies
  getAllActivePolicies() {
    return this._http.get(`${baseUrl}/life-insurances/active/`);
  }

  // Get all bank list
  public getAllBanks() {
    return this._http.get(`${baseUrl}/banks/`);
  }

  // Get all due date mode list
  public getAllPolicyPremiumModes() {
    return this._http.get(`${baseUrl}/premium-modes/`);
  }

  // Filter policies
  public filterPolicies(queryParams: HttpParams) {
    return this._http.get<any>(`${baseUrl}/life-insurances/filter-life-insurance-policy`, { params: queryParams });
  }

  // Get All Active Loans
  getAllActiveLoans() {
    return this._http.get(`${baseUrl}/life-insurances/active/`);
  }

   // get premium by policy id
   public getPremiumByPolicyId(policyId: any) {
    return this._http.get(`${baseUrl}/premiums/life-insurance/${policyId}`);
  }

  public getPolicyById(policyId: any) {
    return this._http.get(`${baseUrl}/life-insurances/${policyId}`);
  }

  updateEmi(addEmi: { policyNo: string; premiumAmount: string; premiumDate: string; premiumStatus: boolean; }) {
    return this._http.post(`${baseUrl}/premiums/`, addEmi);
  }
  public uploadProfilePicture(uploadFile:any){
    return this._http.post(`${baseUrl}/fileController/fileUpload`, uploadFile, { observe: 'response' });
  }

}
