import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { WebLayoutComponent } from '../theme/web-layout/web-layout.component';

const routes: Routes = [
  {
    path: '',
    component: WebLayoutComponent,
    children: [
      { path: '', redirectTo: 'blog', pathMatch: 'full'},
      { 
        path: 'blog',
        loadChildren: () => 
          import('../../proj/blog/blog.module').then(m=>m.BlogModule),
      },{ 
        path: 'navigation',
        loadChildren: () => 
          import('../../proj/navigation/navigation.module').then(m=>m.NavigationModule),
      },{ 
        path: 'person',
        loadChildren: () => 
          import('../../proj/person/person.module').then(m=>m.PersonModule),
      },
    ]
  },{
    path: 'user',
    loadChildren: () => 
          import('../theme/login-layout/login-layout.module').then(m=>m.LoginLayoutModule),
  }
];

@NgModule({
  imports: [
    RouterModule.forRoot(
      routes, {
        useHash: true,
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