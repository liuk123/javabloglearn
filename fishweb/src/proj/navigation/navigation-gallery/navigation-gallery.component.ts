import { ChangeDetectionStrategy, Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Navigation, NavigationItem } from 'src/app/biz/model/navigation';
import { UtilService } from 'src/app/shared';

@Component({
  selector: 'app-navigation-gallery',
  templateUrl: './navigation-gallery.component.html',
  styleUrls: ['./navigation-gallery.component.less'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class NavigationGalleryComponent implements OnInit {

  @Input() navs: Navigation[]=[];
  get columns(){
    return this.util.columnsArr(this.navs, [[],[],[],[]]);
  }

  constructor(
    private router: Router,
    private util: UtilService) { }

  ngOnInit(): void {
  }
  open(item: NavigationItem){
    if(item.type == 'link'){
      window.open(item.url,'_blank');
    }else if(item.type='router'){
      this.router.navigate(['./'+item.route]);
    } 
  }

}
