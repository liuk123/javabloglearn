import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { UtilService } from 'src/app/shared/utils/util';
export class Tags{
  constructor(
    public name: string,
    public icon: string,
    public color?: string,
  ){}
}
@Component({
  selector: 'app-tags',
  templateUrl: './tags.component.html',
  styleUrls: ['./tags.component.less']
})
export class TagsComponent implements OnInit {

  @Input() tagData: Tags[]=[];
  @Input() title: string = "";
  @Input() desc: string = "";
  @Input() selectData: Tags[] = [];
  @Output() selectDataChange: EventEmitter<any[]> = new EventEmitter();
  constructor(
    private util: UtilService
  ) { }

  ngOnInit(): void {
    if(this.tagData.length>0){
      this.tagData.forEach((v, i)=>{
        v.color= this.util.getColors(this.tagData.length)[i]
      })
    }
  }

}
