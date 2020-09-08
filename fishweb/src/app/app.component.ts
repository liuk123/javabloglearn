import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { filter, tap } from 'rxjs/operators';
import { MenuService } from './core/services/menu.service';

@Component({
  selector: 'app-root',
  template: '<router-outlet></router-outlet>'
})
export class AppComponent implements OnInit {
  title = 'web-zorro';

  constructor(
    private router: Router,
    private menuSrv: MenuService
  ) {}
  
  ngOnInit(){
    /**
     * 面包屑菜单
     */
    // this.router.events.pipe(filter((evt) => evt instanceof NavigationEnd)).subscribe((v: NavigationEnd) => {
    //   this.menuSrv.setTitle(v.urlAfterRedirects);
    //   console.log(222)
    //   console.log(v.url)
    // });
  }
}
