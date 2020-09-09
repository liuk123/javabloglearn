import { Component, OnInit, OnDestroy, HostListener, ElementRef } from '@angular/core';

@Component({
  selector: 'app-carousel',
  templateUrl: './carousel.component.html',
  styleUrls: ['./carousel.component.less']
})
export class CarouselComponent implements OnInit, OnDestroy {

  listData: any[] = listData;
  isOffsetPanel: boolean = false;
  timer = null;
  constructor(
    private el: ElementRef
  ) { }

  ngOnInit(): void {
    this.listData.forEach((v,i)=>{
      v.isMax=(i==0);
    })
    this.offsetCarousel();
  }

  offsetCarousel() {
    this.timer = setInterval(v => {
      // console.log("移动了")
      this.listData[1].isMax = true;
      this.listData[0].isMax = false;
      this.isOffsetPanel = true;
      let timerOne = setTimeout(v => {
        // console.log("数组变了")
        
        let val = this.listData.splice(0, 1);
        this.listData.push(val[0])
        this.isOffsetPanel = false;
        clearTimeout(timerOne)
        timerOne = null;
      }, 1500)
    }, 3000)
  }

  @HostListener("mouseenter", ['$event'])
  onMouseOver(ev) {
    ev.preventDefault();
    ev.stopPropagation();
    if (this.el.nativeElement === ev.target) {
      console.log("鼠标移入")
      clearInterval(this.timer)
      this.timer = null;
    }
  }
  @HostListener("mouseleave", ['$event'])
  onMouseOut(ev) {
    ev.preventDefault();
    ev.stopPropagation();
    if (this.el.nativeElement === ev.target) {
      console.log("鼠标移出")
      clearInterval(this.timer)
      this.offsetCarousel();
    }
  }

  ngOnDestroy() {
    // this.unsubscribe$.next();
    // this.unsubscribe$.complete();
  }

}



let listData = [
  {
    
    index: 0,
    title: '标题1',
    dec: '这是一个描述',
    tag: '生活',
    imgUrl: 'http://lackk.com/url/?a=now01bg',
  },
  {
    index: 1,
    title: '标题2',
    dec: '这是一个描述',
    tag: '生活',
    imgUrl: 'http://lackk.com/url/?a=now01bg',
  },
  {
    index: 2,
    title: '标题3',
    dec: '这是一个描述',
    tag: '生活',
    imgUrl: 'http://lackk.com/url/?a=now01bg',
  },
  {
    index: 3,
    title: '标题4',
    dec: '这是一个描述',
    tag: '生活',
    imgUrl: 'http://lackk.com/url/?a=now01bg',
  },
  {
    index: 4,
    title: '标题5',
    dec: '这是一个描述',
    tag: '生活',
    imgUrl: 'http://lackk.com/url/?a=now01bg',
  },
  {
    index: 5,
    title: '标题6',
    dec: '这是一个描述',
    tag: '生活',
    imgUrl: 'http://lackk.com/url/?a=now01bg',
  },
]
