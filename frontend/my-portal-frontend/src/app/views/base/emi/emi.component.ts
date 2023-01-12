import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { LoanService } from '../services/loan.service';
import Swal from 'sweetalert2'
import * as moment from 'moment';
import { MatDatepickerInputEvent } from '@angular/material/datepicker';
import { MatSnackBar } from '@angular/material/snack-bar';


@Component({
  selector: 'app-emi',
  templateUrl: './emi.component.html',
  styleUrls: ['./emi.component.scss']
})
export class EmiComponent implements OnInit {

  constructor(private _route: ActivatedRoute,
    private _loan: LoanService,
    private snackBar: MatSnackBar,
    private router:Router) { }

  loanId: any = 0;
  loans: any = [];
  emis: any = [];
  emiArray: any = [];
  public addEmi = {
    loanNo: '',
    emiAmount: '',
    emiDate: '',
    emiStatus: true
  }

  minDate = new Date();
  date = moment();
  ngOnInit(): void {
    this.loanId = this._route.snapshot.params['loanid'];
    // this.date = null;
    this.getEmiDetails(this.loanId);
    this.fetchLoanDetails();
  }

  addEvent(type: string, event: MatDatepickerInputEvent<Date>) {
    this.date = moment(event.value);
    this.addEmi.emiDate = this.date.format('YYYY-MM-DD');
  }
  getEmiDetails(loanId: string | number) {
    //  alert('LOAN ID: '+loanId);
    this._loan.getEmiByLoanId(this.loanId).subscribe(
      (data: any) => {
        this.emis = data;
        // this.emi.loanNo=this.loans.loanNo;
        console.log('EMI DATA EMIComponent: ', this.emis);
      }, (error: any) => {
        console.log('Error !' + error);
        this.snackBar.open("Some error occured while loading emi details", '', {
          duration: 3000, verticalPosition: 'bottom',
        });
        Swal.fire('Error !!!', 'Error in loading data', 'error');
      }
    );
  }
  chartPieData = {
    labels: ['Total EMI', 'EMI Paid', 'EMI Remaining'],
    datasets: [
      {
        data: this.emiArray,
        backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56'],
        hoverBackgroundColor: ['#FF6384', '#36A2EB', '#FFCE56']
      }
    ]
  };

  fetchLoanDetails(){
  this._loan.getLoanById(this.loanId).subscribe(
    (data: any) => {
      this.loans = data;
      this.addEmi.loanNo=this.loans.loanNo,
      this.addEmi.emiAmount = this.loans.emiAmount;
      this.addEmi.emiStatus = true;
      this.emiArray.push(this.loans.totalEmi);
      this.emiArray.push(this.loans.emiPaid);
      this.emiArray.push(this.loans.emiRemaining);
      // this.getBankById(this.loans.bank);
      // this.emi.loanNo = this.loans.loanNo;
      console.log('LOAN DATA IN EMIComponent: ', this.loans);
    }, (error) => {
      console.log('Error !' + error);
      this.snackBar.open("Some error occured while loading loan details", '', {
        duration: 3000, verticalPosition: 'top',
      });
      Swal.fire('Error !!!', 'Error in loading data', 'error');
    }
  );
  }
  updateEmi() {
    // alert("Update EMI loanStatus: "+this.addEmi.loanStatus);
    if(this.addEmi.emiAmount == '' || this.addEmi.emiAmount == null){
      this.snackBar.open("EMI Amount required !!",'',{
        duration:3000,verticalPosition:'bottom',
      });
      return;
    }
    if(this.addEmi.emiDate == '' || this.addEmi.emiDate == null){
      this.snackBar.open("EMI Date required !!",'',{
        duration:3000,verticalPosition:'bottom',
      });
      return;
    }
    // Validations done
        this._loan.updateEmi(this.addEmi).subscribe(
          (response:any)=>{
            this.addEmi=response;
                console.log('add emi response: '+this.addEmi);
                //  this.successMsg = response.body.errorDescription
                Swal.fire('Success !!!','EMI for Loan No <b>'+this.loans.loanNo+'</b> updated succesfully.','success');
                // this.resetFields();
                // this.toggle();
                this.getEmiDetails(this.loanId);
                this.fetchLoanDetails();
                this.router.navigate(['/base/emi/'+this.loans.loanId]);
                // location.reload();
              
             
          },(error)=>{
            console.log('Error in add-loan', error);
            Swal.fire('Error!!!', 'Loan No. '+this.loans.loanNo+' not added due to server error', 'error' );
          }
        );
  }
}
