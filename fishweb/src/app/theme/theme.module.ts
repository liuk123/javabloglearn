import { NgModule } from '@angular/core';
import { SharedModule } from '../shared/shared.module';
import { WebLayoutModule } from './web-layout/web-layout.module';

const SETTINGDRAWER = [];
const COMPONENTS = [];

@NgModule({
  declarations: [
    ...COMPONENTS,
  ],
  imports: [
    WebLayoutModule
  ],
  entryComponents: SETTINGDRAWER
})
export class ThemeModule { }
