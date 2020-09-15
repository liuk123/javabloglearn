import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ArticleService } from 'src/app/biz/services/blog/article.service';
import { CommentService } from 'src/app/biz/services/blog/comment.service';
import { DialogService } from 'src/app/core/services/dialog.service';
import { TextareaDialogComponent } from 'src/app/shared/components/textarea-dialog/textarea-dialog.component';

@Component({
  selector: 'app-blog-detail',
  templateUrl: './blog-detail.component.html',
  styleUrls: ['./blog-detail.component.less']
})
export class BlogDetailComponent implements OnInit {

  bannerUrl:string = 'https://tvax4.sinaimg.cn/large/6f8a2832gy1gdrcxr3cjpj21kw0xpww8.jpg';
  article:any;
  articleId;
  
  commentList;
  submitting = false;

  constructor(
    private activatedRoute: ActivatedRoute,
    private srv: ArticleService,
    private commentSrv: CommentService,
  ) { }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(v=>{
      this.articleId = v.get('id');
      this.srv.getArticle(v.get('id')).subscribe(res=>{
        if(res.isSuccess()){
          this.article = res.data;
          this.commentList = res.data.commentList;
        }
      })
    })
  }
  commentEvent(data){
    let params={
      content: data,
      fromUserId: 1,
      fromUserName: '123',
      articleId: this.articleId,
    }
    this.submitting = true;
    this.commentSrv.addComment(params).subscribe(res=>{
      this.submitting = false;
      if(res.isSuccess()){
        console.log(res.data);
        this.commentList.unshift(res.data);
      }
    })  
  }
  replyEvent(data){
    console.log(data)
  }
  
}
