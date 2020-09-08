import { NgModule } from '@angular/core';
import { SharedModule } from 'src/app/shared/shared.module';
import { MenuComponent } from './menu/menu.component';
import { BreadcrumbComponent } from './breadcrumb/breadcrumb.component';



@NgModule({
  declarations: [
    MenuComponent,
    BreadcrumbComponent,
  ],
  imports: [
    SharedModule,
  ],
  exports: [
    MenuComponent,
    BreadcrumbComponent,
  ]
})
export class CommonComponentModule { }
