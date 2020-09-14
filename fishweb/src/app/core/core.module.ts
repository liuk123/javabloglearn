import { NgModule, Optional, SkipSelf } from '@angular/core';
import { throwIfAlreadyLoaded } from './module-import-guard';
import { ServicesModule } from '../biz/services/services.module';
import { HttpUtilService } from './services/http-util.service';
import { MessageUtilService } from './services/message-util.service';

@NgModule({
  declarations: [],
  imports: [
    ServicesModule.forRoot(),
  ],
  providers: [HttpUtilService,MessageUtilService]
})
export class CoreModule {
  constructor(@Optional() @SkipSelf() parentModule: CoreModule) {
    throwIfAlreadyLoaded(parentModule, 'CoreModule');
  }
}
