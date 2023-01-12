import { Component, OnInit } from '@angular/core';
// import { DashboardChartsData } from '../../dashboard/dashboard-charts-data';
import { LoanService } from '../services/loan.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import Swal from 'sweetalert2';
import { HttpParams } from '@angular/common/http';
import * as _moment from 'moment';
const moment = _moment;
import { MatDatepickerInputEvent } from '@angular/material/datepicker';
// import { DoughnutController } from 'chart.js';
import { Router } from '@angular/router';


@Component({
  selector: 'app-loans',
  templateUrl: './loans.component.html',
  styleUrls: ['./loans.component.scss']
})
export class LoansComponent implements OnInit {

  constructor(
    private _loan: LoanService,
    private _snack: MatSnackBar,
    private router: Router,
  ) { }

  loans: any = [];
  banks: any = [];
  loanTypes: any = [];
  activeLoans: any = [];
  public filterLoan = {
    loanNo: '',
    loanStatus: '',
    bank: ''
  }
  snackBar: any;
  loanId = '';
  loanStatus = '';

  public addLoan = {
    loanNo: '',
    loanType: '',
    loanAmount: '',
    totalEmi: '',
    emiPaid: 0,
    emiRemaining: 0,
    interestPaid: 0,
    emiAmount: '',
    interestType: '',
    interestRate: '',
    disbursalDate: '',
    firstEmiDate: '',
    lastEmiDate: '',
    loanStatus: true,
    bank: '',
  }

  chartBarData: any;
  chartDoughnutData: any;
  minDate = new Date();
  activeLoanLabelArray: string[] = [];
  activeLoanNoLabelArray: string[] = [];
  activeLoanDataArray: any = [];
  activeLoanColorArray: any = [];
  date1 = moment();
  date2 = moment();
  date3 = moment();

  ngOnInit() {
    // this.date1 = null;
    // this.date2 = null;
    // this.date3 = null;
    this.fetchLoanDetails();
    this.fetchBankList();
    this.fetchAllLoanTypes();
    this.fetchAllActiveLoans();

  }


  addEvent1(type: string, event: MatDatepickerInputEvent<Date>) {
    this.date1 = moment(event.value);
    this.addLoan.disbursalDate = this.date1.format('YYYY-MM-DD');
  }
  addEvent2(type: string, event: MatDatepickerInputEvent<Date>) {
    // alert("addEvent2");
    this.date2 = moment(event.value);
    this.addLoan.firstEmiDate = this.date2.format('YYYY-MM-DD');
  }
  addEvent3(type: string, event: MatDatepickerInputEvent<Date>) {
    this.date3 = moment(event.value);
    this.addLoan.lastEmiDate = this.date3.format('YYYY-MM-DD');
  }


  // loanNumber = ["LPMDB00044944008-ICICI Bank", "23469789-SBI", "PHR063604725475-Axis", "HDFCLAP0120804857-HDFC"];

 


  // Method area
  fetchLoanDetails() {
    this._loan.getAllLoans().subscribe(
      (data: any) => {
        this.loans = data;
        console.log('LOAN DETAILS:', this.loans);
      }, (error) => {
        console.log('Error !' + error);
        this.snackBar.open("Some error occured while loading categories", '', {
          duration: 3000, verticalPosition: 'top',
        });
      }
    );
  }

  fetchBankList() {
    this._loan.getAllBanks().subscribe(
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
  fetchAllLoanTypes() {
    this._loan.getAllLoanTypes().subscribe(
      (data: any) => {
        this.loanTypes = data;
        console.log('LOAN TYPES DETAILS:', this.loanTypes);
      }, (error) => {
        console.log('Error !', error);
        this.snackBar.open("Some error occured while loading bank list", '', {
          duration: 3000, verticalPosition: 'top',
        });
      }
    );
  }
  searchLoan() {
    let queryParams = new HttpParams()
    queryParams = queryParams.append('loanNo', this.filterLoan.loanNo);
    queryParams = queryParams.append('loanStatus', this.filterLoan.loanStatus);
    queryParams = queryParams.append('bankName', this.filterLoan.bank);
    this._loan.filterLoan(queryParams).subscribe(
      (response: any) => {
        this.loans = response;
        console.log('FILTERED LOAN DATA: ' + this.loans);
        //  this.successMsg = response.body.errorDescription
        // Swal.fire('Success !!!','Loan No. '+this.filterLoan.loanNo+' added successfully.','success');
        // this.resetFields();
        this.router.navigate(['/base/loans']);

      }, (error) => {
        console.log('Error in filtering loans', error);
        Swal.fire('Error!!!', 'Error in filtering loans due to server error', 'error');
      }
    );
  }

  fetchAllActiveLoans() {
    // alert("fetchAllActiveLoans");
    this._loan.getAllActiveLoans().subscribe(
      (data: any) => {
        this.activeLoans = data;
        this.activeLoanNoLabelArray.push(this.activeLoans.loanNo);
        for (let activeLoan of this.activeLoans) {
          this.activeLoanLabelArray.push(activeLoan.loanNo);
          this.activeLoanDataArray.push(activeLoan.emiRemaining);
          this.activeLoanColorArray.push(activeLoan.extAttr4);
          this.activeLoanNoLabelArray.push(...activeLoan.loanNo);
       }
    
      // Bar Chart Data
      this.chartBarData = {
        labels:
          [...this.activeLoanLabelArray].slice(0, this.activeLoans.length),
          datasets: [
            {
              label: 'EMI Remaining',
              backgroundColor: '#f87979',
              data: this.activeLoanDataArray
            }
          ]
      };

      // Doughnut Chart Data
      this.chartDoughnutData = {
        labels: this.activeLoanLabelArray,
        datasets: [
          {
            backgroundColor: this.activeLoanColorArray,
            //['#41B883', '#E46651', '#00D8FF', '#DD1B16','#DD1B89'],
            data: this.activeLoanDataArray
          }
        ]
      };
        console.log('ACTIVE LOAN DETAILS:', this.activeLoans);
      }, (error) => {
        console.log('Error !', error);
        this.snackBar.open("Some error occured while loading Active Loan list", '', {
          duration: 3000, verticalPosition: 'top',
        });
      }
    );
  }

  public addNewLoan() {
    // All done
    this._loan.addNewLoan(this.addLoan).subscribe(
      (response: any) => {
        this.addLoan = response;
        console.log('add loan response: ' + this.addLoan);
        //  this.successMsg = response.body.errorDescription
        Swal.fire('Success !!!', 'Loan No. ' + this.addLoan.loanNo + ' added successfully.', 'success');
        // this.resetFields();
        this.router.navigate(['/base/loans']);
      }, (error) => {
        console.log('Error in add-loan', error);
        Swal.fire('Error!!!', 'Loan No. ' + this.addLoan.loanNo + ' not added due to server error', 'error');
      }
    );
  }

  deleteLoan(loanId: any, loanNo: any) {
    console.log("LOAN ID DELETED: " + loanId);
    Swal.fire({
      icon: "question",
      title: 'Are you sure you want to delete Loan No: <b>' + loanNo + '</b> ?',
      confirmButtonText: 'Yes',
      showCancelButton: true,
    }).then((result) => {
      if (result.isConfirmed) {
        //  delete the category
        this._loan.deleteLoan(loanId).subscribe(
          (data: any) => {
            this.loans = this.loans.filter((loan: any) => loan.loanId != loanId);
            Swal.fire('Success !!!', 'Loan No <b>' + loanNo + '</b> deleted successfully !!!', 'success');
          }, (error) => {
            console.log('Error !' + error);
            Swal.fire('Error !!!', 'Error in Deleting Loan No: <b>' + loanNo + '</b>', 'error');
          }
        );
      }
    })
  }
}
