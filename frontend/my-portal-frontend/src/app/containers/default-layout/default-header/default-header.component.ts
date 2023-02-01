import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';

import { ClassToggleService, HeaderComponent } from '@coreui/angular';
import { TaskService } from 'src/app/views/general/services/task.service';

@Component({
  selector: 'app-default-header',
  templateUrl: './default-header.component.html',
})
export class DefaultHeaderComponent extends HeaderComponent implements OnInit {

  @Input() sidebarId: string = "sidebar";

  ngOnInit() {
    // this.fetchAllTasks();
    this.fetchAllActiveTasks();

  }
  public newMessages = new Array(4)
  public newTasks = new Array(5)
  public newNotifications = new Array(5)
  tasks: any = [];
  constructor(private classToggler: ClassToggleService,
    private _task: TaskService,
    private snackBar: MatSnackBar,
    private router: Router) {
    super();
  }

   // Method area
   fetchAllActiveTasks() {
    this._task.fetchAllActiveTasks().subscribe(
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

}
