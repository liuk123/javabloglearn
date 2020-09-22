import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ArticleService } from 'src/app/biz/services/blog/article.service';
import { CommentService } from 'src/app/biz/services/blog/comment.service';

@Component({
  selector: 'app-blog-detail',
  templateUrl: './blog-detail.component.html',
  styleUrls: ['./blog-detail.component.less']
})
export class BlogDetailComponent implements OnInit {

  bannerUrl:string = 'https://tvax4.sinaimg.cn/large/6f8a2832gy1gdkralzwfoj21e00xc13g.jpg';
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
      this.srv.getArticleById(this.articleId).subscribe(res=>{
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
      articleId: this.articleId,
    }
    this.submitting = true;
    this.commentSrv.addComment(params).subscribe(res=>{
      this.submitting = false;
      if(res.isSuccess()){
        this.commentList.unshift(res.data);
      }
    })  
  }
  replyEvent(data){
    let params={
      commentId:data.commentId,
      toUserId:data.toUserId,
      content: data.content,
    }
    this.commentSrv.addReply(params).subscribe(res=>{
      if(res.isSuccess()){
        this.commentList.forEach(v=>{
          if(v.id==data.commentId && Array.isArray(v.replyList)){
            v.replyList.push(res.data);
          }
        })
      }
    })
  }
  
}
