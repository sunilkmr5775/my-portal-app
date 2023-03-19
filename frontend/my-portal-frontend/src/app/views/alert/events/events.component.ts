import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { EventsService } from '../services/events.service';

@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.scss']
})
export class EventsComponent implements OnInit  {

  ngOnInit() {

    this.fetchEventsDetails();
  }

  states = ['normal', 'active', 'disabled'];
  colors = ['primary', 'secondary', 'success', 'danger', 'warning', 'info', 'light', 'dark'];

  constructor( private _events: EventsService,
    private _snack: MatSnackBar,
    private router: Router,
    ) { }
  events: any = [];


    // Method area
    fetchEventsDetails() {
      this._events.getAllEvents().subscribe(
        (data: any) => {
          this.events = data;
          console.log('EVENTS DETAILS:', this.events);
        }, (error) => {
          console.log('Error !' + error);
          this._snack.open("Some error occured while loading events", '', {
            duration: 3000, verticalPosition: 'top',
          });
        }
      );
    }
}
