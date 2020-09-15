import { Injectable } from '@angular/core';
import { NzModalService } from 'ng-zorro-antd/modal';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DialogService {

  constructor(
    private modal: NzModalService
  ) { }

  createModal(title,component,params){
      const modal = this.modal.create({
        nzTitle: title,
        nzContent: component,
        nzGetContainer: () => document.body,
        nzComponentParams: params,
        nzOnOk: () => new Promise(resolve => setTimeout(resolve, 1000)),
      })
      return modal;
  }
}
