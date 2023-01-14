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

  tasks: any = [];
  banks: any = [];
  activeTasks: any = [];
  public filterTask = {
    title: '',
    taskStatus: '',
    
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
    loanStatus:'PENDING'
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
    this.fetchAllTasks();
    // this.fetchAllActiveTasks();

  }


  addEvent1(type: string, event: MatDatepickerInputEvent<Date>) {
    this.date1 = moment(event.value);
    this.addTask.plannedStartDate = this.date1.format('YYYY-MM-DD');
  }
  addEvent2(type: string, event: MatDatepickerInputEvent<Date>) {
    // alert("addEvent2");
    this.date2 = moment(event.value);
    this.addTask.plannedEndDate = this.date2.format('YYYY-MM-DD');
  }


  // Method area
  fetchAllTasks() {
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
    let queryParams = new HttpParams()
    queryParams = queryParams.append('taskTitle', this.filterTask.title);
    queryParams = queryParams.append('taskStatus', this.filterTask.taskStatus);
    // alert(queryParams);
    this._task.filterTask(queryParams).subscribe(
      (response: any) => {
        this.tasks = response;
        console.log('FILTERED TASK DATA: ' + this.tasks);
        //  this.successMsg = response.body.errorDescription
        // Swal.fire('Success !!!','Loan No. '+this.filterLoan.loanNo+' added successfully.','success');
        // this.resetFields();
        this.router.navigate(['/general/task-master']);

      }, (error) => {
        console.log('Error in filtering tasks', error);
        Swal.fire('Error!!!', 'Error in filtering tasks due to server error', 'error');
      }
    );
  }

  // fetchAllActiveTasks() {
  //   // alert("fetchAllActiveLoans");
  //   this._task.getAllActiveTasks().subscribe(
  //     (data: any) => {
  //       this.activeTasks = data;
  //       this.activeLoanNoLabelArray.push(this.activeTasks.loanNo);
  //       for (let activeTask of this.activeTasks) {
  //         this.activeLoanLabelArray.push(activeTask.loanNo);
  //         this.activeLoanDataArray.push(activeTask.emiRemaining);
  //         this.activeLoanColorArray.push(activeTask.extAttr4);
  //         this.activeLoanNoLabelArray.push(...activeTask.loanNo);
  //      }
    
  //     // Bar Chart Data
  //     this.chartBarData = {
  //       labels:
  //         [...this.activeLoanLabelArray].slice(0, this.activeTasks.length),
  //         datasets: [
  //           {
  //             label: 'EMI Remaining',
  //             backgroundColor: '#f87979',
  //             data: this.activeLoanDataArray
  //           }
  //         ]
  //     };

  //     // Doughnut Chart Data
  //     this.chartDoughnutData = {
  //       labels: this.activeLoanLabelArray,
  //       datasets: [
  //         {
  //           backgroundColor: this.activeLoanColorArray,
  //           //['#41B883', '#E46651', '#00D8FF', '#DD1B16','#DD1B89'],
  //           data: this.activeLoanDataArray
  //         }
  //       ]
  //     };
  //       console.log('ACTIVE TASK DETAILS:', this.activeTasks);
  //     }, (error) => {
  //       console.log('Error !', error);
  //       this.snackBar.open("Some error occured while loading Active Task list", '', {
  //         duration: 3000, verticalPosition: 'top',
  //       });
  //     }
  //   );
  // }

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
        this.router.navigate(['/general/task-master']);
      }, (error) => {
        console.log('Error in add-loan', error);
        Swal.fire('Error!!!', 'Title ' + this.addTask.title + ' not added due to server error', 'error');
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
          }, (error) => {
            console.log('Error !' + error);
            Swal.fire('Error !!!', 'Error in Marking task <b>' + title + '</b> as done', 'error');
          }
        );
      }
    })
  }

  markAsUnDone(taskId: any, title: any){
    alert("TASK ID MARKED AS DONE: " + taskId);
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
            this.tasks = this.tasks.filter((task: any) => task.id != taskId);
            Swal.fire('Success !!!', 'Title <b>' + title + '</b> deleted successfully !!!', 'success');
          }, (error) => {
            console.log('Error !' + error);
            Swal.fire('Error !!!', 'Error in Deleting Loan No: <b>' + title + '</b>', 'error');
          }
        );
      }
    })
  }
}
