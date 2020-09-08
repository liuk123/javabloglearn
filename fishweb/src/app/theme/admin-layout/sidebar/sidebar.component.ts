import { Component, OnInit, Input } from '@angular/core';
import { Menu } from 'src/app/core/model/menu.model';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.less']
})
export class SidebarComponent implements OnInit {

  @Input() menu: Menu[];
  constructor() { }

  ngOnInit(): void { }

  open(url:string){
    window.open(url,'_blank');
  }

  openHandler(route: string){
      this.menu.forEach(v=>v.open = v.route == route)
  }
}
