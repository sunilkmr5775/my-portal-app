import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2'
import * as moment from 'moment';
import { MatDatepickerInputEvent } from '@angular/material/datepicker';
import { MatSnackBar } from '@angular/material/snack-bar';
import { LifeInsuranceService } from '../services/life-insurance.service';

@Component({
  selector: 'app-life-insurance-premiums',
  templateUrl: './life-insurance-premiums.component.html',
  styleUrls: ['./life-insurance-premiums.component.scss']
})
export class LifeInsurancePremiumsComponent implements OnInit {

  constructor(private _route: ActivatedRoute,
    private _lifeInsuranceService: LifeInsuranceService,
    private snackBar: MatSnackBar,
    private router: Router) { }

  policyId: any = 0;
  policies: any = [];
  premiums: any = [];
  premiumArray: any = [];
  public addPremium = {
    policyNo: '',
    premiumAmount: '',
    premiumDate: '',
    premiumStatus: true
  }

  minDate = new Date();
  selectedFile?: FileList;
  date = moment();
  jobname = 'BULK_UPLOAD_POLICY_PREMIUM_JOB';
  message = '';
  errorMsg = '';
  posts: {
    body: {
      status: '';
      errorDescription: '';
    };
  } | any;

  ngOnInit(): void {
    this.policyId = this._route.snapshot.params['policyid'];

    // this.date = null;
    this.getPremiumDetails(this.policyId);
    this.fetchPolicyDetails();
  }

  addEvent(type: string, event: MatDatepickerInputEvent<Date>) {
    this.date = moment(event.value);
    this.addPremium.premiumDate = this.date.format('YYYY-MM-DD');
  }



  getPremiumDetails(policyId: string | number) {
    //  alert('Premium ID: '+policyId);
    this._lifeInsuranceService.getPremiumByPolicyId(this.policyId).subscribe(
      (data: any) => {
        this.premiums = data;
        // this.premiums.policyNo=this.policies.policyNo;
        console.log('PREMIUM DATA EMIComponent: ', this.premiums);
      }, (error: any) => {
        console.log('Error !' + error);
        this.snackBar.open("Some error occured while loading PREMIUM details", '', {
          duration: 3000, verticalPosition: 'bottom',
        });
        Swal.fire('Error !!!', 'Error in loading data', 'error');
      }
    );
  }
  chartPieData = {
    labels: ['Total PREMIUMS', 'PREMIUMS Paid', 'PREMIUMS Remaining'],
    datasets: [
      {
        data: this.premiumArray,
        backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56'],
        hoverBackgroundColor: ['#FF6384', '#36A2EB', '#FFCE56']
      }
    ]
  };

  fetchPolicyDetails() {
    this._lifeInsuranceService.getPolicyById(this.policyId).subscribe(
      (data: any) => {
        this.policies = data;
        this.addPremium.policyNo = this.policies.policyNo,
          this.addPremium.premiumAmount = this.policies.premium;
        // alert(this.policies.premium);
        this.addPremium.premiumStatus = true;
        this.premiumArray.push(this.policies.premiumsPaid+this.policies.premiumsRemaining);
        this.premiumArray.push(this.policies.premiumsPaid);
        this.premiumArray.push(this.policies.premiumsRemaining);
        // this.getBankById(this.policies.bank);
        console.log('POLICIES DATA IN Premium Component: ', this.policies);
      }, (error) => {
        console.log('Error !' + error);
        this.snackBar.open("Some error occured while loading LIFE INSURANCE POLICIES", '', {
          duration: 3000, verticalPosition: 'top',
        });
        Swal.fire('Error !!!', 'Error in loading data', 'error');
      }
    );
  }
  updateEmi() {
    // alert("Update PREMIUM loanStatus: "+this.addPremium.loanStatus);
    if (this.addPremium.premiumAmount == '' || this.addPremium.premiumAmount == null) {
      this.snackBar.open("Premium Amount required !!", '', {
        duration: 3000, verticalPosition: 'bottom',
      });
      return;
    }
    if (this.addPremium.premiumDate == '' || this.addPremium.premiumDate == null) {
      this.snackBar.open("Premium Date required !!", '', {
        duration: 3000, verticalPosition: 'bottom',
      });
      return;
    }
    // Validations done
    this._lifeInsuranceService.updateEmi(this.addPremium).subscribe(
      (response: any) => {
        this.addPremium = response;
        console.log('add Premium response: ' + this.addPremium);
        //  this.successMsg = response.body.errorDescription
        Swal.fire('Success !!!', 'Premium for Policy No <b>' + this.policies.policyNo + '</b> updated succesfully.', 'success');
        // this.resetFields();
        // this.toggle();
        //   this.getPremiumDetails(this.policyId);
        this.fetchPolicyDetails();
        this.router.navigate(['/base/life-insurance-premiums/' + this.policies.policyId]);
        // location.reload();


      }, (error) => {
        console.log('Error in add-premium', error);
        Swal.fire('Error!!!', 'Policy No. ' + this.policies.policyNo + ' not added due to server error', 'error');
      }
    );
  }

  public selectFile(event: any): void {
    //Select File
    this.selectedFile = event.target.files;

  }
  uploadFile() {
    this.errorMsg = '';
    this.message = '';
    const uploadFile = new FormData();
    let file: any | File = this.selectedFile?.item(0);
    uploadFile.append('file', file);
    uploadFile.append('jobName', this.jobname);
    uploadFile.append('username', "sunilkmr5775");
    var fileText = "";
    fileText = fileText + '<p align="left"> JOB NAME: <strong>' + this.jobname + '</strong> <br>'
    fileText = fileText + 'USER NAME: <strong>sunilkmr5775</strong> <br>'
    fileText = fileText + 'FILE NAME: <strong>' + file.name + '</strong></p>'
    // Meldungstext = Meldungstext + '<p align="left"> second paragraph block.. with linebreak <br>'
    // Meldungstext = Meldungstext + 'second line to second block </p>' 
    // Meldungstext = Meldungstext + '<p align="left">tex ti third paragraph here </p>'
    // Meldungstext = Meldungstext + '<strong>Last line WITHOUT paragraph = is showed centered (swal default) </strong>'

    Swal.fire({
      icon: "warning",
      title: 'Are you sure you want to upload the file ?',
      // showDenyButton:true,
      confirmButtonText: 'Yes',
      showCancelButton: true,
      html: fileText,
    }).then((result) => {
      if (result.isConfirmed) {
        if (this.selectedFile) {
          if (file) {
            if (!this.validateFile(file.name)) {
              this.errorMsg = 'Only csv file format is supported.';
              return;
            }
            this._lifeInsuranceService.uploadProfilePicture(uploadFile).subscribe(
              (response: any) => {
                if (response.status === 200) {
                  this.posts = response;
                  if (response.body.status == 'FAILURE') {
                    // Swal.fire('Error !!!',response.body.errorDescription,'error');
                    this.errorMsg = response.body.errorDescription;
                    return;
                  } else {
                    console.log('onUpload response: ' + this.posts);
                    console.log('onUpload response response.body.errorDescription: ' + response.body.errorDescription);
                    // alert('onUpload response response.body.errorDescription: ' + response.body.errorDescription);
                    // this.message = response.body.errorDescription
                    this.message = 'File uploaded successfully';
                    this.fetchPolicyDetails();
                    this.getPremiumDetails(this.policyId);
                    this.router.navigate(['/base/life-insurance-premiums/' + this.policies.policyId]);

                  }
                } else {
                  this.errorMsg = 'Some error occured. File not uploaded.';
                  // Swal.fire('Error !!!',this.message,'error');
                }
              });
          }
          this.selectedFile = undefined;
        }
      }
    })
  }

  validateFile(name: String) {
    var ext = name.substring(name.lastIndexOf('.') + 1);
    if (ext.toLowerCase() == 'csv' || ext.toLowerCase() == 'ms-excel') {
      return true;
    }
    else {
      return false;
    }
  }

}
