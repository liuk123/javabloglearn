import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { WebLayoutComponent } from './web-layout.component';
import { HeaderComponent } from './header/header.component';
import { SharedModule } from 'src/app/shared/shared.module';



@NgModule({
  declarations: [
    WebLayoutComponent,
    HeaderComponent
  ],
  imports: [
    SharedModule
  ]
})
export class WebLayoutModule { }
