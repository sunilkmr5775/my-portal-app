import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

import { DocsComponentsModule } from '@docs-components/docs-components.module';
import { ButtonsComponent } from './buttons/buttons.component';
import { ButtonGroupsComponent } from './button-groups/button-groups.component';
import { DropdownsComponent } from './dropdowns/dropdowns.component';

import { AlertRoutingModule } from './alert-routing.module';

import {
  ButtonGroupModule,
  ButtonModule,
  CardModule,
  CollapseModule,
  DropdownModule,
  FormModule,
  GridModule,
  NavbarModule,
  NavModule,
  SharedModule,
  TabsModule,
  UtilitiesModule,
  AccordionModule,
  AvatarModule,
  BadgeModule,
  BreadcrumbModule,
  CalloutModule,
  CarouselModule,
  FooterModule,
  HeaderModule,
  ListGroupModule,
  PaginationModule,
  PlaceholderModule,
  PopoverModule,
  ProgressModule,
  SidebarModule,
  SpinnerModule,
  TableModule,
  TooltipModule,
} from '@coreui/angular';

// CoreUI Modules

import { IconModule } from '@coreui/icons-angular';
import { EventsComponent } from './events/events.component';

@NgModule({
  declarations: [
    ButtonsComponent,
    ButtonGroupsComponent,
    DropdownsComponent,
    EventsComponent,
  ],
  imports: [
    CommonModule,
    AlertRoutingModule,
    ButtonModule,
    ButtonGroupModule,
    GridModule,
    IconModule,
    CardModule,
    UtilitiesModule,
    DropdownModule,
    SharedModule,
    FormModule,
    ReactiveFormsModule,
    DocsComponentsModule,
    NavbarModule,
    CollapseModule,
    NavModule,
    NavbarModule,
    TabsModule,
    AccordionModule,
    AvatarModule,
    BadgeModule,
    BreadcrumbModule,
    CalloutModule,
    CarouselModule,
    FooterModule,
    HeaderModule,
    ListGroupModule,
    PaginationModule,
    PlaceholderModule,
    PopoverModule,
    ProgressModule,
    SidebarModule,
    SpinnerModule,
    TableModule,
    TooltipModule,
  ]
})
export class AlertModule {
}
