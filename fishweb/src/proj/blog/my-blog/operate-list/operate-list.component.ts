import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { PageInfo } from 'src/app/core/model/models.api';

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
  constructor() { }

  ngOnInit(): void {
  }

  onLoadMore(){
    this.loadMoreEvent.emit(this.page.pageNum+1);
  }
  edit(item: any): void {
    // this.msg.success(item.email);
  }

}
