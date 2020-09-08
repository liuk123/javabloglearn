import { Injectable, Inject } from "@angular/core";
import { HttpClient, HttpHeaders, HttpRequest } from '@angular/common/http';

@Injectable()
export class UserService {

    constructor(
        private http: HttpClient,
        @Inject('CONFIG') private config
    ){}

    httpOptions = {
        headers: new HttpHeaders({
            // 'Authorization': '123',
            // "Content-type": "application/x-www-form-urlencoded"
            // "Content-type": "multipart/form-data"
            // 'Content-Type':  'application/json',
        }),
    }

     getUserInfo(){
        const url = `${this.config.url}/api/user`;
        return this.http.get(url)
    }
}