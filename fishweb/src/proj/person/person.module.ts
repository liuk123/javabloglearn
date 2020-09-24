import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PersonHomeComponent } from './person-home/person-home.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { PersonRoutingModule } from './person-routing.module';



@NgModule({
  declarations: [PersonHomeComponent],
  imports: [
    SharedModule,
    PersonRoutingModule
  ]
})
export class PersonModule { }
