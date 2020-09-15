import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { map } from 'rxjs/operators';
import { ArticleService } from 'src/app/biz/services/blog/article.service';

@Component({
  selector: 'app-blog-detail',
  templateUrl: './blog-detail.component.html',
  styleUrls: ['./blog-detail.component.less']
})
export class BlogDetailComponent implements OnInit {

  bannerUrl:string = 'https://tvax4.sinaimg.cn/large/6f8a2832gy1gdrcxr3cjpj21kw0xpww8.jpg';
  article:any;
  comment;
  articleId;
  constructor(
    private activatedRoute: ActivatedRoute,
    private srv: ArticleService,
  ) { }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(v=>{
      this.articleId = v.get('id');
      this.srv.getArticle(v.get('id')).subscribe(res=>{
        if(res.isSuccess()){
          this.article = res.data;
        }
      })
    })
  }

}
