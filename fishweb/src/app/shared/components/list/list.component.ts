import { Component, OnInit, Input, ChangeDetectionStrategy } from '@angular/core';

export class ArtList{
  constructor(
    public title: string,
    public desc: string,
    public author: string,
    public imgUrl: string,
    public content: string,
    public tag: string[],
  ){}
}
@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.less'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ListComponent implements OnInit {

  @Input() listData:ArtList[] = [];
  constructor() { }

  ngOnInit(): void {
  }

}
