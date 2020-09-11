import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { NzCascaderOption } from 'ng-zorro-antd/cascader';

@Component({
  selector: 'app-blog-edit',
  templateUrl: './blog-edit.component.html',
  styleUrls: ['./blog-edit.component.less']
})
export class BlogEditComponent implements OnInit {
  listOfOption: Array<{ label: string; value: string }> = [];
  form: FormGroup;

  constructor(
    private fb: FormBuilder
  ) { }

  ngOnInit(): void {
    this.form = this.fb.group({
      title: [null],
      desc: [null],
      tags: [null],
      content: [null]
    })

    this.listOfOption = listOfOption;
  }

  submitForm(v){
    for (const i in this.form.controls) {
      this.form.controls[i].markAsDirty();
      this.form.controls[i].updateValueAndValidity();
    }

    console.log(v);
  }
}

let listOfOption = [
  {label: '创意', value:"1"},
  {label: '设计', value:"2"},
  {label: '文化', value:"3"},
]