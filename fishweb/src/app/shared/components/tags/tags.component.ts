import { Component, OnInit, Input, Output, EventEmitter, ChangeDetectionStrategy } from '@angular/core';
import { UtilService } from 'src/app/shared/utils/util';
export class Tags{
  constructor(
    public id: number,
    public name: string,
    public icon: string,
    public color?: string,
  ){}
}
@Component({
  selector: 'app-tags',
  templateUrl: './tags.component.html',
  styleUrls: ['./tags.component.less'],
  changeDetection: ChangeDetectionStrategy.OnPush,
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
  selectTags(id){
    let i = this.selectData.indexOf(id)
    if(i==-1){
      this.selectData.push(id);
    }else{
      this.selectData.splice(i,1);
    }
  }

}
