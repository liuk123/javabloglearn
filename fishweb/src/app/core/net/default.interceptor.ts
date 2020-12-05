import { Injectable, Injector } from '@angular/core';
import { Router } from '@angular/router';
import {
  HttpInterceptor,
  HttpRequest,
  HttpHandler,
  HttpErrorResponse,
  HttpEvent,
  HttpResponseBase,
} from '@angular/common/http';
import { Observable, of, throwError } from 'rxjs';
import { mergeMap, catchError } from 'rxjs/operators';
import { MessageUtilService } from '../services/message-util.service';

const CODEMESSAGE = {
  200: '服务器成功返回请求的数据。',
  201: '新建或修改数据成功。',
  202: '一个请求已经进入后台排队（异步任务）。',
  204: '删除数据成功。',
  400: '发出的请求有错误，服务器没有进行新建或修改数据的操作。',
  401: '用户没有权限（令牌、用户名、密码错误）。',
  403: '用户得到授权，但是访问是被禁止的。',
  404: '发出的请求针对的是不存在的记录，服务器没有进行操作。',
  406: '请求的格式不可得。',
  410: '请求的资源被永久删除，且不会再得到的。',
  422: '当创建一个对象时，发生一个验证错误。',
  500: '服务器发生错误，请检查服务器。',
  502: '网关错误。',
  503: '服务不可用，服务器暂时过载或维护。',
  504: '网关超时。',
};
const MAX_RETRY_NUM = 2;

@Injectable()
export class DefaultInterceptor implements HttpInterceptor {

  constructor(
    private message: MessageUtilService
  ){}
  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    const resetReq = req.clone({setHeaders:{'app_key':'123'}})

    let count = 0;

    return next.handle(resetReq).pipe(
      mergeMap((event: any) => {
        return of(event);
      }),
      catchError((err: HttpErrorResponse, err$) => {
        count++;
        

        if(err.status === 400 && count < MAX_RETRY_NUM){
          this.message.error(CODEMESSAGE[err.status]);
          return err$;
        }else{
          return of(err);
        }
        // err$其代表上游的Observable对象，当直接返回这个对象时，会启动catchError的重试机制。
      })
    );
  }
}
