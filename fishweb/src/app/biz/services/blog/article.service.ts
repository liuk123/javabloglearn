import { Inject, Injectable } from '@angular/core';
import { HttpUtilService } from 'src/app/core/services/http-util.service';

@Injectable({
  providedIn: 'root'
})
export class ArticleService {

  articleUrl: string;
  constructor(
    @Inject('CONFIG') private config,
    private http: HttpUtilService,
  ) {
    this.articleUrl = this.config.url + "/api/article/"
  }

  /**
   * 文章保存
   * @param data 
   */
  save(data){
    const url = `${this.articleUrl}`;
    return this.http.post(url, data);
  }

  getArticle(id){
    const url = `${this.articleUrl}${id}`;    
    return this.http.get(url);
  }


}
