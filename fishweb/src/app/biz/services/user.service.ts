import { Inject, Injectable } from '@angular/core';
import { HttpUtilService } from 'src/app/core/services/http-util.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(
    @Inject('CONFIG') private config,
    private http: HttpUtilService,
  ) { }

  getUserInfo(id){
    const url = `${this.config.url}/api/user/${id}`;
    return this.http.get(url)
  }
  addUser(data){
    const url = `${this.config.url}/api/user/`;
    return this.http.post(url, data);
  }
}
