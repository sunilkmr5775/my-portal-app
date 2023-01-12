import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LifeInsuranceComponent } from './life-insurance/life-insurance.component';
import { CardsComponent } from './cards/cards.component';
import { CarouselsComponent } from './carousels/carousels.component';
import { CollapsesComponent } from './collapses/collapses.component';
import { ListGroupsComponent } from './list-groups/list-groups.component';
import { NavsComponent } from './navs/navs.component';
import { PaginationsComponent } from './paginations/paginations.component';
import { PopoversComponent } from './popovers/popovers.component';
import { ProgressComponent } from './progress/progress.component';
import { SpinnersComponent } from './spinners/spinners.component';
import { TablesComponent } from './tables/tables.component';
import { TooltipsComponent } from './tooltips/tooltips.component';
import { TabsComponent } from './tabs/tabs.component';
import { PlaceholdersComponent } from './placeholders/placeholders.component';
import { LoansComponent } from './loans/loans.component';
import { EmiComponent } from './emi/emi.component';
import { LifeInsurancePremiumsComponent } from './life-insurance-premiums/life-insurance-premiums.component';
import { FileUploadComponent } from './file-upload/file-upload.component';
// import { EmiComponent } from './emi/emi.component';

const routes: Routes = [
  {
    path: '',
    data: {
      title: 'Base',
    },
    children: [
      {
        path: '',
        pathMatch: 'full',
        redirectTo: 'cards',
      },
      {
        path: 'loans',
        component: LoansComponent,
        data: {
          title: 'Loan',
        },
      },
      {
        path: 'emi/:loanid',
        component: EmiComponent,
        data: {
          title: 'Loan Details',
        },
      },
      {
        path: 'life-insurance',
        component: LifeInsuranceComponent,
        data: {
          title: 'life-Insurance',
        },
      },
      {
        path: 'life-insurance-premiums/:policyid',
        component: LifeInsurancePremiumsComponent,
        data: {
          title: 'Life Insurance Premiums Details',
        },
      },
      {
        path: 'file-upload/:jobname',
        component: FileUploadComponent
      },
      {
        path: 'cards',
        component: CardsComponent,
        data: {
          title: 'Cards',
        },
      },
      {
        path: 'carousel',
        component: CarouselsComponent,
        data: {
          title: 'Carousel',
        },
      },
      {
        path: 'collapse',
        component: CollapsesComponent,
        data: {
          title: 'Collapse',
        },
      },
      {
        path: 'list-group',
        component: ListGroupsComponent,
        data: {
          title: 'List Group',
        },
      },
      {
        path: 'navs',
        component: NavsComponent,
        data: {
          title: 'Navs & Tabs',
        },
      },
      {
        path: 'pagination',
        component: PaginationsComponent,
        data: {
          title: 'Pagination',
        },
      },
      {
        path: 'placeholder',
        component: PlaceholdersComponent,
        data: {
          title: 'Placeholder',
        },
      },
      {
        path: 'popovers',
        component: PopoversComponent,
        data: {
          title: 'Popovers',
        },
      },
      {
        path: 'progress',
        component: ProgressComponent,
        data: {
          title: 'Progress',
        },
      },
      {
        path: 'spinners',
        component: SpinnersComponent,
        data: {
          title: 'Spinners',
        },
      },
      {
        path: 'tables',
        component: TablesComponent,
        data: {
          title: 'Tables',
        },
      },
      {
        path: 'tabs',
        component: TabsComponent,
        data: {
          title: 'Tabs',
        },
      },
      {
        path: 'tooltips',
        component: TooltipsComponent,
        data: {
          title: 'Tooltips',
        },
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class BaseRoutingModule {}

