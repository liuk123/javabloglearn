import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { userInfo } from '../model/user.model';

@Injectable({
  providedIn: 'root',
})
export class CommonService {
  userInfo: userInfo = {};
  public userSource = new BehaviorSubject(this.userInfo);
}
