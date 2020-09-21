import { Component, OnInit, Input, ChangeDetectionStrategy, Output, EventEmitter } from '@angular/core';
import { ArtList } from 'src/app/biz/model/article';

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
