import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-person-home',
  templateUrl: './person-home.component.html',
  styleUrls: ['./person-home.component.less']
})
export class PersonHomeComponent implements OnInit {

  bannerUrl:string = 'https://tvax4.sinaimg.cn/large/6f8a2832gy1gdkralzwfoj21e00xc13g.jpg';
  constructor() { }

  ngOnInit(): void {
  }

}
