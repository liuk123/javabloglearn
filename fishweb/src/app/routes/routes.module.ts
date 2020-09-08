import { NgModule } from '@angular/core';
import { RouteRoutingModule } from './routes-routing.module';

const COMPONENTS = [
 
];
const COMPONENTS_NOROUNT = [
  
];

@NgModule({
  declarations: [
    ...COMPONENTS,
    ...COMPONENTS_NOROUNT
  ],
  imports: [
    RouteRoutingModule,
  ],
  entryComponents: COMPONENTS_NOROUNT,
})
export class RoutesModule { }
