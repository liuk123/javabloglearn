import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { WebLayoutComponent } from './web-layout.component';
import { HeaderComponent } from './header/header.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { BreadcrumbComponent } from './breadcrumb/breadcrumb.component';



@NgModule({
  declarations: [
    WebLayoutComponent,
    HeaderComponent,
    BreadcrumbComponent,
  ],
  imports: [
    SharedModule
  ]
})
export class WebLayoutModule { }
