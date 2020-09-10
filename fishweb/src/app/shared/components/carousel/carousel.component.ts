import { Component, OnInit, HostListener, ElementRef, Input, OnDestroy } from '@angular/core';
import { UtilService } from '../../utils/util';

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
export class CarouselComponent implements OnInit, OnDestroy {

  @Input() carouselData: CarouselData[] = [];
  isOffsetPanel: boolean = false;
  isShowMaxDetail: boolean = false;
  timer = null;
  maxData:CarouselData;
  
  colors:string[] = [];
  colorCurrent: string = "rgb(217, 5, 160)"
  
  constructor(
    private el: ElementRef,
    private util: UtilService,
  ) { }

  ngOnInit(): void {
    this.colors = this.util.getColors(this.carouselData.length);
    this.carouselData.forEach((v,i)=>{
      v.isMax=(i==0);
      // v.color = this.colors[i];
    })
    this.offsetCarousel();
    this.maxData = this.carouselData[0];
  }
  ngOnDestroy(){
    clearInterval(this.timer)
  }

  offsetCarousel() {
    this.timer = setInterval(v => {
      if(this.carouselData.length==0){ return false}
      this.maxData = this.carouselData[1];
      this.carouselData[1].isMax = true;
      this.carouselData[0].isMax = false;
      this.isOffsetPanel = true;
      let timerOne = setTimeout(v => {
        let val = this.carouselData.splice(0, 1);
        this.carouselData.push(val[0])
        this.isOffsetPanel = false;
        this.colorCurrent = this.colors[this.maxData.index];
        timerOne = null;
      }, 1500)
    }, 8000)
  }

  @HostListener("mouseenter", ['$event'])
  onMouseOver(ev) {
    ev.preventDefault();
    ev.stopPropagation();
    if (this.el.nativeElement === ev.target) {
      clearInterval(this.timer)
      this.timer = null;
    }
  }
  @HostListener("mouseleave", ['$event'])
  onMouseOut(ev) {
    ev.preventDefault();
    ev.stopPropagation();
    if (this.el.nativeElement === ev.target) {
      // clearInterval(this.timer)
      this.offsetCarousel();
    }
  }

  
}