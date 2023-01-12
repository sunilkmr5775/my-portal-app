import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.scss']
})
export class FileUploadComponent implements OnInit {

  constructor(private _active: ActivatedRoute) { }

  jobname = '';
  
  ngOnInit(): void {
    this.jobname=this._active.snapshot.params['jobname'];
  }

}
