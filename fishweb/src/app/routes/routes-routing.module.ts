import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { environment } from 'src/environments/environment';
import { AdminLayoutComponent } from '../theme/admin-layout/admin-layout.component';

const routes: Routes = [
  {
    path: '',
    component: AdminLayoutComponent,
    children: [
      // { path: '', redirectTo: 'demo', pathMatch: 'full'},
      // { 
      //   path: 'demo',
      //   loadChildren: () => 
      //     import('../../demo/demo.module').then(m=>m.DemoModule),
      // },
    ]
  },
];

@NgModule({
  imports: [
    RouterModule.forRoot(
      routes, {
        // useHash: environment.useHash,
        scrollPositionRestoration: 'top',
      }
    )],
  exports: [RouterModule],
})
export class RouteRoutingModule { }
// router.events.pipe(
//   *       filter((e: Event): e is Scroll => e instanceof Scroll)
//   *     ).subscribe(e => {
//   *       if (e.position) {
//   *         // backward navigation
//   *         viewportScroller.scrollToPosition(e.position);
//   *       } else if (e.anchor) {
//   *         // anchor navigation
//   *         viewportScroller.scrollToAnchor(e.anchor);
//   *       } else {
//   *         // forward navigation
//   *         viewportScroller.scrollToPosition([0, 0]);
//   *       }
//   *     });