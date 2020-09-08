import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-carousel',
  templateUrl: './carousel.component.html',
  styleUrls: ['./carousel.component.less']
})
export class CarouselComponent implements OnInit {

  listData=listData;

  constructor() { }

  ngOnInit(): void {
  }

}



let listData = [
  {
    index: 0,
    title:'标题1',
    dec:'这是一个描述',
    tag:'生活',
    imgUrl:'http://lackk.com/url/?a=now01bg',
  },
  {
    index: 0,
    title:'标题1',
    dec:'这是一个描述',
    tag:'生活',
    imgUrl:'http://lackk.com/url/?a=now01bg',
  },
  {
    index: 0,
    title:'标题1',
    dec:'这是一个描述',
    tag:'生活',
    imgUrl:'http://lackk.com/url/?a=now01bg',
  },
  {
    index: 0,
    title:'标题1',
    dec:'这是一个描述',
    tag:'生活',
    imgUrl:'http://lackk.com/url/?a=now01bg',
  },
  {
    index: 0,
    title:'标题1',
    dec:'这是一个描述',
    tag:'生活',
    imgUrl:'http://lackk.com/url/?a=now01bg',
  },
  {
    index: 0,
    title:'标题1',
    dec:'这是一个描述',
    tag:'生活',
    imgUrl:'http://lackk.com/url/?a=now01bg',
  },
]
