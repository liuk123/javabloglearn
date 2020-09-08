import { Injectable } from '@angular/core';
import {
  HttpInterceptor,
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpResponse,
} from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { filter, tap } from 'rxjs/operators';



@Injectable()
export class CacheInterceptor implements HttpInterceptor {
  static cachebleUrlList = ['/deliverboard/v1/userBoard/userInfo'];
  static cacheRequetMap = new Map();

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {

    if(!this.canCacherReq(req)){
      return next.handle(req)
    }

    const cachedResponse = CacheInterceptor.cacheRequetMap.get(req.url);
    if(cachedResponse){
      return of(cachedResponse);
    }

    return next.handle(req).pipe(
      filter(event => event instanceof HttpResponse),
      tap(event => {
        console.log(event, '响应事件');
        CacheInterceptor.cacheRequetMap.set(req.url, event);
      })
    );
  }

  // 判断当前请求是否需要缓存
  canCacherReq(req: HttpRequest<any>): boolean {
    return CacheInterceptor.cachebleUrlList.indexOf(req.url) !== -1;
  }

  // 查询缓存的接口列表
  static getCachedUrlList(): string[] {
    return [...CacheInterceptor.cacheRequetMap.keys()];
  }

  // 外部主动刷新
  static refreshReq(req) {
    CacheInterceptor.cacheRequetMap.delete(req);
  }
}
