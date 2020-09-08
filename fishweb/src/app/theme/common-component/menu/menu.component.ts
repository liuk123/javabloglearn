import { Component, OnInit, Input } from '@angular/core';
import { Menu } from 'src/app/core/model/menu.model';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.less']
})
export class MenuComponent implements OnInit {

  @Input() menus: Menu[];
  constructor() { }

  ngOnInit(): void {
  }

  openHandler(route: string){
    this.menus.forEach(v=>v.open = v.route == route)
  }
  open(url:string){
    window.open(url,'_blank');
  }

}
