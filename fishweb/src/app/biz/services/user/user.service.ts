import { Inject, Injectable } from '@angular/core';
import { HttpUtilService } from 'src/app/core/services/http-util.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  userUrl: string;
  constructor(
    @Inject('CONFIG') private config,
    private http: HttpUtilService,
  ) {
    this.userUrl = this.config.url + "/user/"
  }

  /**
   * 
   * @param id 获取用户信息
   */
  getUserInfo(id){
    const url = `${this.userUrl}${id}`;
    return this.http.get(url)
  }
  /**
   * 用户注册
   * @param data
   */
  register(data){
    const url = this.userUrl;
    return this.http.post(url, data);
  }

  /**
   * 用户登录
   * @param data 
   */
  login(data){
    const url = `${this.config.url}/user/login`;    
    let params = this.http.encodeParams(data);
    return this.http.get(url,{params});
  }

}
