import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AuditorRoutingModule } from './auditor-routing.module';
import { SharedModule } from '../shared/shared.module';


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    AuditorRoutingModule,
    SharedModule

  ]
})
export class AuditorModule { }
