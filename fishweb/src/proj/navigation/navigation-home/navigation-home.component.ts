import { HttpClient } from '@angular/common/http';
import { ChangeDetectionStrategy, ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { Navigation } from 'src/app/biz/model/navigation';

@Component({
  selector: 'app-navigation-home',
  templateUrl: './navigation-home.component.html',
  styleUrls: ['./navigation-home.component.less'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class NavigationHomeComponent implements OnInit {

  navs:Navigation[] = []
  bannerUrl:string = 'https://tvax4.sinaimg.cn/large/6f8a2832gy1gdkralzwfoj21e00xc13g.jpg';

  searchBoxValue: string = '';
  get searchValue(){
    return encodeURIComponent(this.searchBoxValue)
  }
  set searchValue(v){
    this.searchBoxValue = v
  }
  searchUriData=[];

  constructor(
    private http: HttpClient,
    private cf: ChangeDetectorRef
    ) { }

  ngOnInit(): void {
    this.http.get('assets/data/navigation.json').subscribe((res:Navigation[])=>{
      this.navs = res;
      this.cf.markForCheck()
    })
    this.http.get('assets/data/search.json').subscribe((res:[])=>{
      this.searchUriData = res;
      this.cf.markForCheck()
    })
  }

  search(searchUri:string,indexUri:string = ''){
    if(this.searchValue){
      window.open(searchUri + this.searchValue, '_blank')
    }else{
      window.open(indexUri, '_blank')
    }
  }

}
