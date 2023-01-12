import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { JobMasterComponent } from './job-master/job-master.component';
import { TaskComponent } from './task/task.component';



const routes: Routes = [
  {
    path: '',
    data: {
      title: 'General'
    },
    children: [
      // {
      //   path: 'general',
      //   pathMatch: 'full',
      //   redirectTo: 'cards',
      // },
      {
        path: 'job-master',
        component: JobMasterComponent,
        data: {
          title: 'Job Master'
        }
      },
      {
        path: 'task-master',
        component: TaskComponent,
        data: {
          title: 'Task Master'
        }
      },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class GeneralRoutingModule {
}
