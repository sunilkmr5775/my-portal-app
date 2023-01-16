import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { DatabasesService } from '../../general/services/databases.service';

@Component({
  selector: 'app-database-information',
  templateUrl: './database-information.component.html',
  styleUrls: ['./database-information.component.scss']
})
export class DatabaseInformationComponent implements OnInit {

  constructor(private _loan: DatabasesService,
    private _snack: MatSnackBar,
    private router: Router) { }

  ngOnInit(): void {
    this.fetchDBDetails()
  }
  databases: any = [];
  pssword: any = '';

  public showPass = {
    password: '',
  }
  button: any = ''

  // Method area
  fetchDBDetails() {
    this._loan.getDbInfo().subscribe(
      (data: any) => {
        this.databases = data;
        this.pssword = this.convertPassword(this.databases.password)
        this.button = "Show";
        console.log('DB DETAILS:', this.databases);
      }, (error) => {
        console.log('Error !' + error);
        this._snack.open("Some error occured while loading categories", '', {
          duration: 3000, verticalPosition: 'top',
        });
      }
    );
  }
 
  showPassword(button: any) {
    if (this.button === "Show") {
      this.pssword = this.databases.password;
      this.button = "Hide";
    }
    else if (this.button === "Hide") {
      this.pssword = this.convertPassword(this.databases.password);
      this.button = "Show";
    }
  }
  convertPassword(password: string | any[]) {
    let pass = password.toString();
    let encryPass = '*';
    for (let i = 0; i < password.length - 1; i++) {
      encryPass = encryPass + '*';
    }
    return encryPass;
  }
}
