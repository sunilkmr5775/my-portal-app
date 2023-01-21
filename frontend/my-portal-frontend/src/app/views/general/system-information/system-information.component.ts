import { Component, OnInit } from '@angular/core';
import { SystemInformationService } from '../services/system-information.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import Swal from 'sweetalert2';
import { HttpParams } from '@angular/common/http';
import * as _moment from 'moment';
const moment = _moment;
import { MatDatepickerInputEvent } from '@angular/material/datepicker';
import { Router } from '@angular/router';
import { UntypedFormGroup, UntypedFormControl } from '@angular/forms';
import { DashboardChartsData, IChartProps } from '../../dashboard/dashboard-charts-data';

interface IUser {
  name: string;
  state: string;
  registered: string;
  country: string;
  usage: number;
  period: string;
  payment: string;
  activity: string;
  avatar: string;
  status: string;
  color: string;
}

@Component({
  selector: 'app-system-information',
  templateUrl: './system-information.component.html',
  styleUrls: ['./system-information.component.scss']
})
export class SystemInformationComponent implements OnInit {

  constructor(private _systemInformationService: SystemInformationService,
    private _snack: MatSnackBar,
    private router: Router,
    private chartsData: DashboardChartsData) {

  }

  ngOnInit(): void {
    this.getSystemInfoDetails();
  }

  systemInfo: any = [];
  systemInfoArray: string[] = [];
  chartBarData:any;
  chartDoughnutData:any;
  // Method area
  getSystemInfoDetails() {
    this._systemInformationService.getSystemInformation().pipe().subscribe(
      (data: any) => {
        this.systemInfo = data;
        this.systemInfoArray.push(this.systemInfo.current);
        this.systemInfoArray.push(this.systemInfo.used);
        this.systemInfoArray.push(this.systemInfo.free);
        this.systemInfoArray.push(this.systemInfo.maximumHeap);
        console.log('SYSTEM INFORMATION:', this.systemInfo);
        this.getChartData(this.systemInfoArray);
        this.getChartDoughnutData(this.systemInfoArray);
      }, (error) => {
        console.log('Error !' + error);
        this._snack.open("Some error occured while loading system information", '', {
          duration: 3000, verticalPosition: 'top',
        });
      }
    );
  }
  
param = ['Current-Heap-Size', 'Used-Heap', 'Free Memory', 'Total JVM'];
getChartData(systemInfoArray:any){  
  this.chartBarData = {
    labels: [...this.param].slice(0, 4),
    datasets: [
      {
        label: 'System Information(in GB)',
        backgroundColor: '#CB4335',
        data: systemInfoArray
      }
    ]
  };}

  getChartDoughnutData(systemInfoArray:any){
    this.chartDoughnutData = {
      labels: ['Current-Heap-Size(in GB)', 'Used-Heap(in GB)', 'Free Memory(in GB)', 'Total JVM(in GB)'],
      datasets: [
        {
          backgroundColor: ['#DD1B16','#00D8FF', '#FFFF00','#41B883'],
          data: systemInfoArray
        }
      ]
    };
  }

}
