import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Navigation, NavigationItem } from 'src/app/biz/model/navigation';

@Component({
  selector: 'app-navigation-gallery',
  templateUrl: './navigation-gallery.component.html',
  styleUrls: ['./navigation-gallery.component.less']
})
export class NavigationGalleryComponent implements OnInit {

  @Input() columns:Navigation[][]
  constructor(private router: Router) { }

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
