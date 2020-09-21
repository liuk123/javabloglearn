import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
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
    private router: Router
  ) { }

  ngOnInit(): void {
    this.form = this.fb.group({
      title: [null],
      desc: [null],
      tagList: [null],
      content: [null]
    })

    this.listOfOption = listOfOption;
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