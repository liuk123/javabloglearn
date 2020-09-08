import { NgModule } from '@angular/core';
import { UploadService } from './upload.service';
import { UserService } from './user.service';

export {
  // AuthGuardService
}
@NgModule()
export class ServicesModule {
  static forRoot(){
    return {
      ngModule: ServicesModule,
      providers: [
        UploadService,
        UserService,
      ]
    }
  }
}
