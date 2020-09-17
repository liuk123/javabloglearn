import { HttpClient } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { Navigation } from 'src/app/biz/model/navigation';
import { UtilService } from 'src/app/shared';

@Component({
  selector: 'app-navigation-home',
  templateUrl: './navigation-home.component.html',
  styleUrls: ['./navigation-home.component.less']
})
export class NavigationHomeComponent implements OnInit {

  columns:Navigation[][] = [[],[],[]];
  navs:Navigation[] = []
  constructor(
    private util: UtilService,
    private http: HttpClient
    ) { }

  ngOnInit(): void {
    this.http.get('assets/data/navigation.json').subscribe((res:Navigation[])=>{
      this.navs = res;
      this.util.columnsArr(res, this.columns);
    })
  }

}
