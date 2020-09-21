import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { User } from '../model/user.model';

@Injectable({
  providedIn: 'root',
})
export class CommonService {
  userInfo: User = {};
  public userSource = new BehaviorSubject(this.userInfo);
}
