import { Component, OnInit, Input } from '@angular/core';
import { Menu } from 'src/app/core/model/menu.model';
import { User } from 'src/app/core/model/user.model';
import { CommonService } from 'src/app/core/services/common.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.less']
})
export class HeaderComponent implements OnInit {

  @Input() menus: Menu[];
  userInfo: User;
  constructor(
    private commonSrv: CommonService
  ) { }

  ngOnInit(): void {
    this.commonSrv.userSource.subscribe(v=>this.userInfo = v);
  }

  openHandler(route: string){
    this.menus.forEach(v=>v.open = v.route == route)
  }
  open(url:string){
    window.open(url,'_blank');
  }

}
