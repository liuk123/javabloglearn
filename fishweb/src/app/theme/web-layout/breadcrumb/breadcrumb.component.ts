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
    console.log(this.breadcrumbMenus)
  }

}
