import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ArticleService } from 'src/app/biz/services/blog/article.service';

@Component({
  selector: 'app-blog-edit',
  templateUrl: './blog-edit.component.html',
  styleUrls: ['./blog-edit.component.less']
})
export class BlogEditComponent implements OnInit {
  listOfOption: Array<{ label: string; value: number }> = [];
  form: FormGroup;

  constructor(
    private fb: FormBuilder,
    private srv: ArticleService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
  ) {
    this.form  = this.fb.group({
      id: [null],
      title: [null],
      desc: [null],
      tagList: [null],
      content: [null]
    })
    this.listOfOption = listOfOption;
  }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(v=>{
      if(v.get('id') != null){
        this.srv.getArticleById(v.get('id')).subscribe(res=>{
          if(res.isSuccess()){
            this.form.patchValue({
              id: res.data.id,
              title: res.data.title,
              desc: res.data.desc,
              tagList: res.data.tagList.map(v=>v.id),
              content: res.data.content,
            })
          }
        })
      }
    })
  }

  submitForm(v){
    this.form.markAllAsTouched();
    this.form.updateValueAndValidity();
    
    this.srv.save(v).subscribe(res=>{
      if(res.isSuccess()){
        this.router.navigate(['./blog/detail', {id: res.data}]);
      }
    })
  }
}

let listOfOption = [
  {label: '创意', value:1},
  {label: '设计', value:2},
  {label: '文化', value:3},
]