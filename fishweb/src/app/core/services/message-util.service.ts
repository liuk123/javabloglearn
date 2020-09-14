import { Injectable } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd/message';
import { Result } from '../model/result.model';
import { HttpResponseAlertStatus } from '../model/http-response-alert-status.model';
import { Observable } from 'rxjs';
import { NzModalService } from 'ng-zorro-antd/modal';

@Injectable({
  providedIn: 'root'
})
export class MessageUtilService {


  MSG_SUCESS = "操作成功！";
  MSG_ERROR = "操作失败！";
  MSG_WARN = "操作有误！";

  constructor(private message: NzMessageService,
    private modalService: NzModalService) { }

  /**
  * 默认的消息提醒
  * @param resust:返回的结果
  * @param status:成功或失败是否提醒
  */
  default(resust: Result, status = HttpResponseAlertStatus.ALL) {
    if (!resust.isSuccess) {
      console.log(resust);
    }
    if (resust && resust.isSuccess() && (status === HttpResponseAlertStatus.ALL || status === HttpResponseAlertStatus.SUCCESS)) {
      this.success(resust.resultMsg);
    } else if (resust && !resust.isSuccess() && (status === HttpResponseAlertStatus.ALL || status === HttpResponseAlertStatus.FAIL)) {
      this.error(resust.resultMsg);
    }
  }

  /**
   * 操作成功的消息
   * @param message:需要提醒的消息
   */
  success(message = this.MSG_SUCESS): void {
    this.message.create('success', message);
  }

  /**
   * 操作失败的消息
   * @param message:需要提醒的消息
   */
  error(message = this.MSG_ERROR): void {
    this.message.create('error', message);
  }

  /**
   * 操作有误的消息
   * @param message:需要提醒的消息
   */
  warning(message = this.MSG_WARN): void {
    this.message.create('warning', message);
  }

  confirm({ okText = '确定', cancelText = '取消', content = '你已经填写了部分表单，离开会放弃已填写的内容。', title = '确认要离开吗？' } = {}): Observable<any> {
    return new Observable(observer => {
      this.modalService.confirm({
        nzTitle: title,
        nzContent: content,
        nzOkText: okText,
        nzCancelText: cancelText,
        nzOnOk: () => {
          observer.next(true);
          observer.complete();
        },
        nzOnCancel: () => {
          observer.next(false);
          observer.complete();
        }
      });
    });
  }
}
