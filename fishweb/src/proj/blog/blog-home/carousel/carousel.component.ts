import { Component, OnInit, HostListener, ElementRef, Input } from '@angular/core';

export class CarouselData{
  constructor(
    public index:string,
    public title:string,
    public desc:string,
    public tag:string,
    public imgUrl:string,
    public isMax?:boolean,

  ){}  
}

@Component({
  selector: 'app-carousel',
  templateUrl: './carousel.component.html',
  styleUrls: ['./carousel.component.less']
})
export class CarouselComponent implements OnInit {

  @Input() listData: CarouselData[] = [];
  isOffsetPanel: boolean = false;
  timer = null;
  maxData:CarouselData;
  
  constructor(
    private el: ElementRef
  ) { }

  ngOnInit(): void {
    this.listData.forEach((v,i)=>{
      v.isMax=(i==0);
    })
    this.offsetCarousel();
    this.maxData = this.listData[0];
  }

  offsetCarousel() {
    this.timer = setInterval(v => {
      // console.log("移动了")
      if(this.listData.length==0){ return false}
      this.maxData = this.listData[1];
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

  getColors(n){
    let r = 0;
    let colors = [];
    for(let i=0; i<n; i++){
      r-=Math.PI*2/-300
      colors.push(
        '#'+ (
               1<<24|
              Math.cos(r)*127+128<<16 |
              Math.cos(r+Math.PI*2/3)*127+128<<8 |
              Math.cos(r+Math.PI*4/3)*127+128).toString(16).slice(1)
      )
    }
    return colors;
  }
}