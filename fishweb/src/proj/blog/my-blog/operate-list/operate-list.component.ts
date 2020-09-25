import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-operate-list',
  templateUrl: './operate-list.component.html',
  styleUrls: ['./operate-list.component.less']
})
export class OperateListComponent implements OnInit {

  initLoading = true;
  isShowMoreBtn: boolean = true;

  _page;
  @Input() set page(v){
    this.initLoading = false;
    this.isShowMoreBtn = !v.loading && (v.pages > v.pageNum);
    this._page = v;
  }
  get page(){
    return this._page;
  }
  @Output() loadMoreEvent = new EventEmitter<null>()
  @Output() editEvent = new EventEmitter<number>()
  @Output() delEvent = new EventEmitter<number>()
  @Output() openEvent = new EventEmitter<number>()
  constructor() { }

  ngOnInit(): void {
  }

  onLoadMore(){
    this.loadMoreEvent.emit(this.page.pageNum+1);
  }
  edit(id: any): void {
    this.editEvent.emit(id)
  }
  del(id){
    this.delEvent.emit(id)
  }
  open(id){
    this.openEvent.emit(id)
  }

}
