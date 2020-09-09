import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-blog-home',
  templateUrl: './blog-home.component.html',
  styleUrls: ['./blog-home.component.less']
})
export class BlogHomeComponent implements OnInit {
  listData=listData
  constructor() { }

  ngOnInit(): void {
  }

}
let listData = [
  {
    
    index: 0,
    title: '标题1',
    desc: '分享别人的生活家，小巧精致、舒适恰意。多看看别人的房间，再对比自己的，这是努力向前的动力。',
    tag: '生活',
    imgUrl: 'http://lackk.com/url/?a=now01bg',
  },
  {
    index: 1,
    title: '标题2',
    desc: '这是一个描述',
    tag: '生活',
    imgUrl: 'http://lackk.com/url/?a=now01bg',
  },
  {
    index: 2,
    title: '标题3',
    desc: '这是一个描述',
    tag: '生活',
    imgUrl: 'http://lackk.com/url/?a=now01bg',
  },
  {
    index: 3,
    title: '标题4',
    desc: '这是一个描述',
    tag: '生活',
    imgUrl: 'http://lackk.com/url/?a=now01bg',
  },
  {
    index: 4,
    title: '标题5',
    desc: '这是一个描述',
    tag: '生活',
    imgUrl: 'http://lackk.com/url/?a=now01bg',
  },
  {
    index: 5,
    title: '标题6',
    desc: '这是一个描述',
    tag: '生活',
    imgUrl: 'http://lackk.com/url/?a=now01bg',
  },
]