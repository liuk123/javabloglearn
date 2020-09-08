import { NgModule } from '@angular/core';
import { SharedModule } from '../shared/shared.module';
import { AdminLayoutComponent } from './admin-layout/admin-layout.component';
import { SidebarComponent } from './admin-layout/sidebar/sidebar.component';
import { HeaderComponent } from './admin-layout/header/header.component';
import { BreadcrumbComponent } from './admin-layout/breadcrumb/breadcrumb.component';
import { FooterComponent } from './admin-layout/footer/footer.component';

const SETTINGDRAWER = [];
const COMPONENTS = [];

@NgModule({
  declarations: [
    ...COMPONENTS,
    AdminLayoutComponent,
    SidebarComponent,
    HeaderComponent,
    BreadcrumbComponent,
    FooterComponent,
  ],
  imports: [
    SharedModule,
  ],
  entryComponents: SETTINGDRAWER
})
export class ThemeModule { }
