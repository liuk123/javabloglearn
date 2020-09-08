import { Component, OnInit, Input } from '@angular/core';
import { Menu } from 'src/app/core/model/menu.model';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.less']
})
export class HeaderComponent implements OnInit {

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
