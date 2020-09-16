import { Component, OnInit, Input } from '@angular/core';
import { BreadcrumbMenu } from 'src/app/core/model/menu.model';

@Component({
  selector: 'app-breadcrumb',
  templateUrl: './breadcrumb.component.html',
  styleUrls: ['./breadcrumb.component.less']
})
export class BreadcrumbComponent implements OnInit {

  @Input() breadcrumbMenus: BreadcrumbMenu[] = [];
  constructor() { }

  ngOnInit(): void {
  }
  open(url){
    window.open(url, '_blank');
  }
}
