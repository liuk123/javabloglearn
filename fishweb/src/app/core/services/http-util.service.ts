import {Injectable} from '@angular/core';
import {HttpClient, HttpRequest, HttpResponse} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {catchError, filter, map} from 'rxjs/operators';
import {MessageUtilService} from './message-util.service';
import {HttpResponseAlertStatus} from '../model/http-response-alert-status.model';
import {Result} from '../model/result.model';

@Injectable({
  providedIn: 'root',
})
export class HttpUtilService {

  constructor(private http: HttpClient,
              private messageUtil: MessageUtilService) {
  }

  /** GET请求处理（一般用于获取数据） **/
  get(url: string, httpOptions: any = {}, status: HttpResponseAlertStatus = HttpResponseAlertStatus.ALL): Observable<Result | any> {
    return this.http.get<Result | any>(url, httpOptions).pipe(
      map(restResponse => {
        return this.callback(restResponse, status);
      }),
      catchError(this.handleError(url, [])),
    );
  }

  /** POST请求处理（一般用于保存数据） **/
  post(url: string, data: any = {}, httpOptions: any = {}, status: HttpResponseAlertStatus = HttpResponseAlertStatus.ALL): Observable<Result> {
    return this.http.post<Result>(url, data, httpOptions).pipe(
      map(restResponse => {
        return this.callback(restResponse, status);
      }),
      catchError(this.handleError(url, [])),
    );
  }

  /** PUT请求处理（一般用于更新数据） **/
  put(url: string, data: any = {}, httpOptions: any = {}, status: HttpResponseAlertStatus = HttpResponseAlertStatus.ALL): Observable<Result | any> {
    return this.http.put<Result | any>(url, data, httpOptions).pipe(
      map(restResponse => {
        return this.callback(restResponse, status);
      }),
      catchError(this.handleError(url, [])),
    );
  }

  /** DELETE请求处理（一般用于删除数据） **/
  delete(url: string, data: any = {}, status: HttpResponseAlertStatus = HttpResponseAlertStatus.ALL): Observable<Result | any> {
    return this.http.delete<Result | any>(url, data).pipe(
      map(restResponse => {
        return this.callback(restResponse, status);
      }),
      catchError(this.handleError(url, [])),
    );
  }

  /**
   * 文件上传
   * @param url：后台服务地址
   * @param files：文件
   * @param params:其他参数
   * @param fileKey：文件key，默认为files
   * @param method：请求方式，默认为post
   * @param headers：headers
   */
  upload(url: string, {files = null, params = null,fileKey = 'files',method = 'POST'}={}, {headers=null}={}): Observable<any> {
    const formData = new FormData();
    if(files){
      files.forEach((file: any) => {
        formData.append(fileKey, file);
      });
    }
    if(params){
      Object.keys(params).forEach(key => {
        formData.set(key, params[key]);
      });
    }
    const req = new HttpRequest(method, url, formData, {
      headers,
      reportProgress:true,
    });
    return this.http.request(req).pipe(filter(e => e instanceof HttpResponse));
  }

  callback(response, status: HttpResponseAlertStatus) {
    response = Result.init(response);
    if (!response) {
      this.messageUtil.error('未获取到数据！');
    }
    if (status !== HttpResponseAlertStatus.NONE) {
      this.messageUtil.default(response, status);
    }
    return response;
  }

  /**
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead
      // TODO: better job of transforming error for user consumption
      console.log(`${operation} failed: ${error.message}`);
      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  // /**
  //  * 获取全部url参数,并转换成对象
  //  * */
  // getUrlParams(url) {
  //   var url = url ? url : window.location.href;
  //   var _pa = url.substring(url.indexOf('?') + 1),
  //     _arrS = _pa.split('&'),
  //     _rs = {};
  //   for (var i = 0, _len = _arrS.length; i < _len; i++) {
  //     var pos = _arrS[i].indexOf('=');
  //     if (pos == -1) {
  //       continue;
  //     }
  //     var name = _arrS[i].substring(0, pos),
  //       value = decodeURIComponent(_arrS[i].substring(pos + 1));
  //     _rs[name] = value;
  //   }
  //   return _rs;
  // }
}
