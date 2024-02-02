import { Component, OnInit } from '@angular/core';
// import { DashboardChartsData } from '../../dashboard/dashboard-charts-data';
import { TaskService } from '../services/task.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import Swal from 'sweetalert2';
import { HttpParams } from '@angular/common/http';
import * as _moment from 'moment';
const moment = _moment;
import { MatDatepickerInputEvent } from '@angular/material/datepicker';
// import { DoughnutController } from 'chart.js';
import { Router } from '@angular/router';


@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.scss']
})
export class TaskComponent implements OnInit {

  constructor(
    private _task: TaskService,
    private _snack: MatSnackBar,
    private router: Router,
  ) { }

  currentYear = new Date().getFullYear();
  currentMonth = //new Date().getUTCMonth()+1;
  ('0' + (new Date().getMonth()+1)).slice(-2);

  tasks: any = [];
  // updateTasks: any = [];
  banks: any = [];
  activeTasks: any = [];
  public filterTask = {
    title: '',
    taskStatus: '',
    year:this.currentYear,
    month:this.currentMonth
    
  }
  snackBar: any;
  taskId = '';
  loanStatus = '';

  public addTask = {
    title: '',
    description: '',
    plannedStartDate: '',
    plannedEndDate: '',
    actualStartDate: '',
    actualEndDate: '',
    priority:'',
    taskStatus:'PENDING',
    isEmailReminder:true,
  }
  public updateTask = {
    id:'',
    title: '',
    description: '',
    plannedStartDate1: '',
    plannedEndDate1: '',
    actualStartDate: '',
    actualEndDate: '',
    priority:'',
    taskStatus:'PENDING',
    reminderRequired:true,
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
  updateDate1 = moment();
  updateDate2 = moment();
  years: any;
  isTaskRollover: boolean = false;
  // isEmailReminder: boolean = false;
  public rollOverTask={
    selectAll: false
  }
  showRolloverSubmitButton: any = false;
  months = [
    {"no":"01","name":"January"}, 
    {"no":"02","name":"February"},
    {"no":"03","name":"March"},
    {"no":"04","name":"April"},
    {"no":"05","name":"May"},
    {"no":"06","name":"June"},
    {"no":"07","name":"July"},
    {"no":"08","name":"August"},
    {"no":"09","name":"September"},
    {"no":"10","name":"October"},
    {"no":"11","name":"November"},
    {"no":"12","name":"December"}
  ];

  ngOnInit() {
    this.fetchAllTasks();
    // this.fetchAllActiveTasks();
    this.getUploadPeriod();
    this.searchTask();
    // this.isTaskRollover=false;
    // alert("current month: "+this.currentMonth);

  }

  getUploadPeriod() {
    this.years = this.serviceGetUploadPeriod();
    console.log(this.years);
  }

  public serviceGetUploadPeriod() {
    let baseYear = new Date().getFullYear()-5;
    let currYear = new Date().getFullYear();
    let years = [];

    for (var i = currYear; i > baseYear; i--) {
      years.push(i);
    }
   return years;
  }

  addEvent1(type: string, event: MatDatepickerInputEvent<Date>) {
    this.date1 = moment(event.value);
    this.addTask.plannedStartDate = this.date1.format('YYYY-MM-DD');
  }
  addEvent2(type: string, event: MatDatepickerInputEvent<Date>) {
    this.date2 = moment(event.value);
    this.addTask.plannedEndDate = this.date2.format('YYYY-MM-DD');
  }
  updateEvent1(type: string, event: MatDatepickerInputEvent<Date>) {
    this.updateDate1 = moment(event.value);
    this.updateTask.plannedStartDate1 = this.updateDate1.format('YYYY-MM-DD');
  }
  updateEvent2(type: string, event: MatDatepickerInputEvent<Date>) {
    this.updateDate2 = moment(event.value);
    this.updateTask.plannedEndDate1 = this.updateDate2.format('YYYY-MM-DD');
  }
  // currentDate:Date = new Date();
  // currentDateTime = this.currentDate.getTime();
  // date = this.currentDateTime - (90 * 24 * 60 * 60 * 1000);
  // oldDate:Date = new Date(this.date);

  // Method area
  fetchAllTasks() {
    this.showRolloverSubmitButton = false;
    this.isTaskRollover=false;
    this._task.getAllTasks().subscribe(
      (data: any) => {
        this.tasks = data;
        
        console.log('LOAN DETAILS:', this.tasks);
      }, (error) => {
        console.log('Error !' + error);
        this.snackBar.open("Some error occured while loading categories", '', {
          duration: 3000, verticalPosition: 'top',
        });
      }
    );
  }

  searchTask() {
    this.showRolloverSubmitButton = false;
    let queryParams = new HttpParams()
    queryParams = queryParams.append('taskTitle', this.filterTask.title);
    queryParams = queryParams.append('taskStatus', this.filterTask.taskStatus);
    queryParams = queryParams.append('taskYear', this.filterTask.year);
    queryParams = queryParams.append('taskMonth', this.filterTask.month);
    this._task.filterTask(queryParams).subscribe(
      (response: any) => {
        this.tasks = response;
        console.log('FILTERED TASK DATA: ' + this.tasks);
        //  this.successMsg = response.body.errorDescription
        // Swal.fire('Success !!!','Loan No. '+this.filterLoan.loanNo+' added successfully.','success');
        // this.resetFields();
        this.router.navigate(['/general/task-master']);
        this.isTaskRollover=false;

      }, (error) => {
        console.log('Error in filtering tasks', error);
        Swal.fire('Error!!!', 'Error in filtering tasks due to server error', 'error');
      }
    );
  }

  fetchAllActiveTasks() {
    this._task.fetchAllActiveTasks().subscribe(
      (data: any) => {
        this.tasks = data;
        this.isTaskRollover=false;
        console.log('LOAN DETAILS:', this.tasks);
      }, (error) => {
        console.log('Error !' + error);
        this.snackBar.open("Some error occured while loading categories", '', {
          duration: 3000, verticalPosition: 'top',
        });
      }
    );
  }

  public addNewTask() {
    // All done
    this._task.addNewTask(this.addTask).subscribe(
      (response: any) => {
        this.addTask = response;
        console.log('Add Task response: ' + this.addTask);
        //  this.successMsg = response.body.errorDescription
        Swal.fire('Success !!!', 'Title ' + this.addTask.title + ' added successfully.', 'success');
        // this.resetFields();
        this.fetchAllTasks();
        this.isTaskRollover=false;
        // this.fetchAllActiveTasks();
        this.router.navigate(['/general/task-master']);
      }, (error) => {
        console.log('Error in add-loan', error);
        Swal.fire('Error!!!', 'Title ' + this.addTask.title + ' not added due to server error', 'error');
      }
    );
  }

  updateSingleTask(taskId:any){
    for (let item of this.tasks) {
      if(taskId==item.id){
        this.updateTask = item;
     }
    }
  }
  updateExistingTask(){
     this._task.updateTask(this.updateTask).subscribe(
      (response: any) => {
        this.updateTask = response;
        console.log('Update Task response: ' + this.addTask);
        //  this.successMsg = response.body.errorDescription
        Swal.fire('Success !!!', 'Title ' + this.updateTask.title + ' updated successfully.', 'success');
        // this.resetFields();
        this.fetchAllTasks();
        this.isTaskRollover=false;
        // this.fetchAllActiveTasks();
        this.router.navigate(['/general/task-master']);
      }, (error) => {
        console.log('Error in add-loan', error);
        Swal.fire('Error!!!', 'Title ' + this.updateTask.title + ' not updated due to server error', 'error');
      }
    );
  }

  markAsDone(taskId: any, title: any){
    console.log("TASK ID MARKED AS DONE: " + taskId);
    Swal.fire({
      icon: "question",
      title: 'Are you sure you want to mark task <b>' + title + '</b> as done ?',
      confirmButtonText: 'Yes',
      showCancelButton: true,
    }).then((result) => {
      if (result.isConfirmed) {
        //  delete the category
        this._task.markAsDone(taskId).subscribe(
          (data: any) => {
           // this.tasks = this.tasks.filter((task: any) => task.id != taskId);
            Swal.fire('Success !!!', 'Task <b>' + title + '</b> marked as done successfully !!!', 'success');
            this.fetchAllTasks();
            this.isTaskRollover=false;
          }, (error) => {
            console.log('Error !' + error);
            Swal.fire('Error !!!', 'Error in Marking task <b>' + title + '</b> as done', 'error');
          }
        );
      }
    })
  }

  markAsUnDone(taskId: any, title: any){
    Swal.fire({
      icon: "question",
      title: 'Are you sure you want to mark task <b>' + title + '</b> as undone ?',
      confirmButtonText: 'Yes',
      showCancelButton: true,
    }).then((result) => {
      if (result.isConfirmed) {
        //  delete the category
        this._task.markAsUndone(taskId).subscribe(
          (data: any) => {
           // this.tasks = this.tasks.filter((task: any) => task.id != taskId);
            Swal.fire('Success !!!', 'Task <b>' + title + '</b> marke as undone!!!', 'success');
            this.fetchAllTasks();
            this.isTaskRollover=false;
          }, (error) => {
            console.log('Error !' + error);
            Swal.fire('Error !!!', 'Error in Marking task <b>' + title + '</b> as undone', 'error');
          }
        );
      }
    })
  }

  deleteTask(taskId: any, title: any) {
    console.log("TASK ID DELETED: " + taskId);
    Swal.fire({
      icon: "question",
      title: 'Are you sure you want to delete title <b>' + title + '</b> ?',
      confirmButtonText: 'Yes',
      showCancelButton: true,
    }).then((result) => {
      if (result.isConfirmed) {
        //  delete the category
        this._task.deleteTask(taskId).subscribe(
          (data: any) => {
            this.fetchAllTasks();
            this.isTaskRollover=false;
            //this.tasks = this.tasks.filter((task: any) => task.id != taskId);
            Swal.fire('Success !!!', 'Title <b>' + title + '</b> deleted successfully !!!', 'success');
          }, (error) => {
            console.log('Error !' + error);
            Swal.fire('Error !!!', 'Error in Deleting Loan No: <b>' + title + '</b>', 'error');
          }
        );
      }
    })
  }


  public toggle() {
    this.isTaskRollover = !this.isTaskRollover;
    // CHANGE THE NAME OF THE BUTTON.
   // if (this.isTaskRollover)
      // this.buttonName = "Show EMI Summary";
    // else
      // this.buttonName = "Update EMI";
  }
  public toggleEmailReminder() {
    this.addTask.isEmailReminder = !this.addTask.isEmailReminder;
  }

  public toggleReminder() {
    this.updateTask.reminderRequired = !this.updateTask.reminderRequired;
  }
  

  public toggleSelectAll() {
    // alert(this.tasks.length)
    for (let item of this.tasks) {
      item.selected = this.rollOverTask.selectAll;
    }
  }
  
  rollOverTaskArray: any = [];
  
  public toggleSelectItem(item:any) {
    if (!item.selected) {
      this.rollOverTask.selectAll = false;
      this.rollOverTaskArray.push(item.id);
      this.showRolloverSubmitButton = true;
    } else {
      this.rollOverTask.selectAll = this.tasks.every((item:any) => item.selected);
      //this.rollOverTaskArray.push(item);
    }
  }

  
  public rollOverTasks() {
    // const taskId = [1.0, 2.5, 3.7, 4.2];
    Swal.fire({
      icon: "question",
      title: 'Are you sure you want to rollover <b>' + this.rollOverTaskArray.length + ' task(s) </b> to current month?',
      confirmButtonText: 'Yes',
      showCancelButton: true,
    }).then((result) => {
      if (result.isConfirmed) {
        //  delete the category
        this._task.rollOverTask(this.rollOverTaskArray).subscribe(
          (response) => {
            console.log('PUT request successful:', response);
            Swal.fire('Success !!!', 'Selected task rolled over successfully to current month!!!', 'success');
           // this.isTaskRollover=false;
            this.fetchAllTasks();
            
          }, (error) => {
            console.error('PUT request error:', error);
            Swal.fire('Error !!!', 'Error in Rolling over task to current month', 'error');
          }
        );
      }
    })
  }


}


