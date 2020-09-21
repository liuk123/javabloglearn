import { Component, OnInit, Input, ChangeDetectionStrategy, Output, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';

export class ArtList{
  constructor(
    public id: string,
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
  @Output() OpenEvent: EventEmitter<string> = new EventEmitter();
  constructor(
  ) { }

  ngOnInit(): void {
  }
  open(id){
    this.OpenEvent.emit(id);
  }
}
