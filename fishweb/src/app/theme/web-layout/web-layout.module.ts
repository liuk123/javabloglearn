import { NgModule } from '@angular/core';
import { WebLayoutComponent } from './web-layout.component';
import { HeaderComponent } from './header/header.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { CommonComponentModule } from '../common-component/common-component.module';



@NgModule({
  declarations: [
    WebLayoutComponent,
    HeaderComponent,
  ],
  imports: [
    SharedModule,
    CommonComponentModule
  ]
})
export class WebLayoutModule { }
