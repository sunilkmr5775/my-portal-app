import { Component, OnInit } from '@angular/core';
import { JobMasterService } from '../services/job-master.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import Swal from 'sweetalert2';
import { HttpParams } from '@angular/common/http';
import * as _moment from 'moment';
const moment = _moment;
import { MatDatepickerInputEvent } from '@angular/material/datepicker';
import { Router } from '@angular/router';

@Component({
  selector: 'app-job-master',
  templateUrl: './job-master.component.html',
  styleUrls: ['./job-master.component.scss']
})
export class JobMasterComponent implements OnInit {

  constructor(
    private _jobMasterService: JobMasterService,
    private _snack: MatSnackBar,
    private router: Router
  ) { }

  jobMaster: any = [];
  jobs: any = [];
  activeJobs: any = [];
  dueDateModes: any = [];
  public searchJob = {
    jobName: '',
    jobStatus: ''
  }
  snackBar: any;
  policyId = '';
  policyStatus = '';

  public addJob = {
    jobName: '',
    fileType: '',
    fileInPath: '',
    fileOutPath: '',
    jobStatus: "ACTIVE",

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
    this.getAllJobs();
    this.fetchJobList();

  }


  // Method area
  getAllJobs() {
    this._jobMasterService.getAllJobs().subscribe(
      (data: any) => {
        this.jobMaster = data;
        console.log('JOB MASTER DETAILS:', this.jobMaster);
      }, (error) => {
        console.log('Error !' + error);
        this.snackBar.open("Some error occured while loading categories", '', {
          duration: 3000, verticalPosition: 'top',
        });
      }
    );
  }


  fetchJobList() {
    this._jobMasterService.getAllJobs().subscribe(
      (data: any) => {
        this.jobs = data;
        console.log('JOB DETAILS:', this.jobs);
      }, (error) => {
        console.log('Error !', error);
        this.snackBar.open("Some error occured while loading JOB list", '', {
          duration: 3000, verticalPosition: 'top',
        });
      }
    );
  }

  searchJobDetails() {
    let queryParams = new HttpParams()
    queryParams = queryParams.append('jobName', this.searchJob.jobName);
    queryParams = queryParams.append('jobStatus', this.searchJob.jobStatus);
    // alert(queryParams);
    this._jobMasterService.searchJobs(queryParams).subscribe(
      (response: any) => {
        this.jobMaster = response;
        console.log('FILTERED JOB DATA: ' + this.jobMaster);
        //  this.successMsg = response.body.errorDescription
        // Swal.fire('Success !!!','Policy No. '+this.filterPolicy.policyNo+' added successfully.','success');
        // this.resetFields();
        this.router.navigate(['/general/job-master']);

      }, (error) => {
        console.log('Error in searching job', error);
        Swal.fire('Error!!!', 'Error in searching job due to server error', 'error');
      }
    );
  }

  public addNewJob() {
    this._jobMasterService.addJob(this.addJob).subscribe(
      (response: any) => {
        this.addJob = response;
        console.log('ADD JOB RESPONSE: ' + this.addJob);
        //  this.successMsg = response.body.errorDescription
        Swal.fire('Success !!!', 'Job ' + this.addJob.jobName + ' added successfully.', 'success');
        // this.resetFields();
        this.addJob.jobName='';
        this.getAllJobs();
        this.router.navigate(['/general/job-master']);
      }, (error) => {
        console.log('Error in adding job', error);
        Swal.fire('Error!!!', 'Job ' + this.addJob.jobName + ' not added due to server error', 'error');
      }
    );
  }

  deleteJob(jobId: any, jobName: any) {
    alert("JOB ID DELETED: " + jobId);
    console.log("JOB ID DELETED: " + jobId);
    Swal.fire({
      icon: "question",
      title: 'Are you sure you want to delete Job: <b>' + jobName + '</b> ?',
      confirmButtonText: 'Yes',
      showCancelButton: true,
    }).then((result) => {
      if (result.isConfirmed) {
        this._jobMasterService.deleteJob(jobId).subscribe(
          (data: any) => {
            this.jobMaster = this.jobMaster.filter((job: any) => job.id != jobId);
            Swal.fire('Success !!!', 'Job <b>' + jobName + '</b> deleted successfully !!!', 'success');
          }, (error) => {
            console.log('Error !' + error);
            Swal.fire('Error !!!', 'Error in Deleting Policy No: <b>' + jobName + '</b>', 'error');
          }
        );
      }
    })
  }
}

