import { Inject, Injectable } from '@angular/core';
import { HttpUtilService } from 'src/app/core/services/http-util.service';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  commentUrl: string;
  replyUrl: string;
  constructor(
    @Inject('CONFIG') private config,
    private http: HttpUtilService,
  ) {
    this.commentUrl = this.config.url + "/comment/"
    this.replyUrl = this.config.url + "/reply/"
  }

  /**
   * 评论保存
   * @param data 
   */
  addComment(data){
    const url = `${this.commentUrl}`;
    return this.http.post(url, data);
  }
  /**
   * 回复保存
   * @param data 
   */
  addReply(data){
    const url = `${this.replyUrl}`;
    return this.http.post(url, data);
  }

  // getArticle(id){
  //   const url = `${this.commentUrl}${id}`;    
  //   return this.http.get(url);
  // }


}
