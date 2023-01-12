import { Component, OnInit } from '@angular/core';
// import { DashboardChartsData } from '../../dashboard/dashboard-charts-data';
import { LifeInsuranceService } from '../services/life-insurance.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import Swal from 'sweetalert2';
import { HttpParams } from '@angular/common/http';
import * as _moment from 'moment';
const moment = _moment;
import { MatDatepickerInputEvent } from '@angular/material/datepicker';
// import { DoughnutController } from 'chart.js';
import { Router } from '@angular/router';
import { formatDate } from '@angular/common';


@Component({
  selector: 'app-life-insurance',
  templateUrl: './life-insurance.component.html',
  styleUrls: ['./life-insurance.component.scss']
})
export class LifeInsuranceComponent implements OnInit {

  constructor(
    private _lifeInsurance: LifeInsuranceService,
    private _snack: MatSnackBar,
    private router: Router,
  ) { }

  lifeInsurance: any = [];
  banks: any = [];
  activePolicies: any = [];
  dueDateModes: any = [];
  public filterPolicy = {
    policyNo: '',
    policyStatus: '',
    bank: ''
  }
  snackBar: any;
  policyId = '';
  policyStatus = '';

  public addPolicy = {

    bank: '',
    policyNo: '',
    planName: '',

    sumAssured: 0,
    premium: 0,
    dueDateMode: '',

    commencementDate: '',
    lastPaymentDate: '',
    maturityDate: '',


    policyTerm: 0,
    premiumPayingTerm: 0,
    premiumsPaid: 0,

    premiumsRemaining: 0,
    nominee: '',
    policyStatus: "ACTIVE",
    status: true,
    imgPath: './assets/img/avatars',
    // htmlColor1: '',
    // htmlColor2: '',
    // hexColor: '',

  }

  chartBarData: any;
  chartDoughnutData: any;
  minDate = new Date();
  activePolicyLabelArray: string[] = [];
  activePolicyNoLabelArray: string[] = [];
  activePolicyDataArray: any = [];
  activePolicyColorArray: any = [];
  date1 = moment();
  date2 = moment();
  date3 = moment();

  ngOnInit() {
    //  this.date1 = null;
    // this.date2 = null;
    // this.date3 = null;
    this.fetchPolicyDetails();
    this.fetchBankList();
    this.fetchAllActivePolicies();
    this.fetchPolicyPremiumModes();

  }


  addEvent1(type: string, event: MatDatepickerInputEvent<Date>) {
    this.date1 = moment(event.value);
    this.addPolicy.commencementDate = this.date1.format('YYYY-MM-DD');
  }
  addEvent2(type: string, event: MatDatepickerInputEvent<Date>) {
    // alert("addEvent2");
    this.date2 = moment(event.value);
    this.addPolicy.lastPaymentDate = this.date2.format('YYYY-MM-DD');
  }
  addEvent3(type: string, event: MatDatepickerInputEvent<Date>) {
    this.date3 = moment(event.value);
    this.addPolicy.maturityDate = this.date3.format('YYYY-MM-DD');
  }


  // Method area
  fetchPolicyDetails() {
    this._lifeInsurance.getAllPolicies().subscribe(
      (data: any) => {
        this.lifeInsurance = data;
        console.log('LIFE ISNURANCE POLICY DETAILS:', this.lifeInsurance);
      }, (error) => {
        console.log('Error !' + error);
        this.snackBar.open("Some error occured while loading categories", '', {
          duration: 3000, verticalPosition: 'top',
        });
      }
    );
  }
  fetchPolicyPremiumModes() {
    this._lifeInsurance.getAllPolicyPremiumModes().subscribe(
      (data: any) => {
        this.dueDateModes = data;
        console.log('PREMIUM MODES DETAILS:', this.dueDateModes);
      }, (error) => {
        console.log('Error !', error);
        this.snackBar.open("Some error occured while loading bank list", '', {
          duration: 3000, verticalPosition: 'top',
        });
      }
    );
  }

  fetchBankList() {
    this._lifeInsurance.getAllBanks().subscribe(
      (data: any) => {
        this.banks = data;
        console.log('BANK DETAILS:', this.banks);
      }, (error) => {
        console.log('Error !', error);
        this.snackBar.open("Some error occured while loading bank list", '', {
          duration: 3000, verticalPosition: 'top',
        });
      }
    );
  }

  searchLifeInsurance() {
    let queryParams = new HttpParams()
    queryParams = queryParams.append('policyNo', this.filterPolicy.policyNo);
    queryParams = queryParams.append('policyStatus', this.filterPolicy.policyStatus);
    queryParams = queryParams.append('bankName', this.filterPolicy.bank);
    // alert(queryParams);
    this._lifeInsurance.filterPolicies(queryParams).subscribe(
      (response: any) => {
        this.lifeInsurance = response;
        console.log('FILTERED LIFE INSURANCE DATA: ' + this.lifeInsurance);
        //  this.successMsg = response.body.errorDescription
        // Swal.fire('Success !!!','Policy No. '+this.filterPolicy.policyNo+' added successfully.','success');
        // this.resetFields();
        this.router.navigate(['/base/life-insurance']);

      }, (error) => {
        console.log('Error in filtering Life Insurance', error);
        Swal.fire('Error!!!', 'Error in filtering Life Insurance due to server error', 'error');
      }
    );
  }

  fetchAllActivePolicies() {
    // alert("fetchAllActivePolicies");
    this._lifeInsurance.getAllActivePolicies().subscribe(
      (data: any) => {
        this.activePolicies = data;
        this.activePolicyNoLabelArray.push(this.activePolicies.policyNo);
        for (let activePolicy of this.activePolicies) {
          this.activePolicyLabelArray.push(activePolicy.policyNo+"-"+activePolicy.planName);
          this.activePolicyDataArray.push(activePolicy.premiumsRemaining);
          this.activePolicyColorArray.push(activePolicy.hexColor);
          this.activePolicyNoLabelArray.push(...activePolicy.policyNo);
          
        }
        // alert("activePolicyLabelArray: "+this.activePolicyLabelArray+"\nactivePolicyDataArray: "+this.activePolicyDataArray+
        // "\nactivePolicyColorArray: "+this.activePolicyColorArray+"\nactivePolicyNoLabelArray: "+this.activePolicyNoLabelArray);
        // Bar Chart Data
        this.chartBarData = {
          labels:
            [...this.activePolicyLabelArray].slice(0, this.activePolicies.length),
          datasets: [
            {
              label: 'Premiums Remaining',
              backgroundColor: '#008080',
              data: this.activePolicyDataArray
            }
          ]
        };

        // Doughnut Chart Data
        this.chartDoughnutData = {
          labels: this.activePolicyLabelArray,
          datasets: [
            {
              backgroundColor: this.activePolicyColorArray,
              //['#41B883', '#E46651', '#00D8FF', '#DD1B16','#DD1B89'],
              data: this.activePolicyDataArray
            }
          ]
        };
        console.log('ACTIVE POLICIES DETAILS:', this.activePolicies);
      }, (error) => {
        console.log('Error !', error);
        this.snackBar.open("Some error occured while loading Active Life Insurance Policy list", '', {
          duration: 3000, verticalPosition: 'top',
        });
      }
    );
  }

  public addNewPolicy() {
    // All done
    this._lifeInsurance.addNewPolicy(this.addPolicy).subscribe(
      (response: any) => {
        this.addPolicy = response;
        console.log('add life-insurance response: ' + this.addPolicy);
        //  this.successMsg = response.body.errorDescription
        Swal.fire('Success !!!', 'Policy No. ' + this.addPolicy.policyNo + ' added successfully.', 'success');
        // this.resetFields();
        this.fetchPolicyDetails();
        this.router.navigate(['/base/life-insurance']);
      }, (error) => {
        console.log('Error in add-life-insurance', error);
        Swal.fire('Error!!!', 'Policy No. ' + this.addPolicy.policyNo + ' not added due to server error', 'error');
      }
    );
  }

  deletePolicy(policyId: any, policyNo: any) {
    console.log("POLICY ID DELETED: " + policyId);
    Swal.fire({
      icon: "question",
      title: 'Are you sure you want to delete POLICY No: <b>' + policyNo + '</b> ?',
      confirmButtonText: 'Yes',
      showCancelButton: true,
    }).then((result) => {
      if (result.isConfirmed) {
        //  delete policy
        this._lifeInsurance.deletePolicy(policyId).subscribe(
          (data: any) => {
            this.lifeInsurance = this.lifeInsurance.filter((policy: any) => policy.policyId != policyId);
            Swal.fire('Success !!!', 'Policy No <b>' + policyNo + '</b> deleted successfully !!!', 'success');
          }, (error) => {
            console.log('Error !' + error);
            Swal.fire('Error !!!', 'Error in Deleting Policy No: <b>' + policyNo + '</b>', 'error');
          }
        );
      }
    })
  }
}
