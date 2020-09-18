import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from 'src/app/biz/services/user/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.less']
})
export class LoginComponent implements OnInit {

  form: FormGroup;

  constructor(private fb: FormBuilder,
    private srv:UserService,
    private router:Router) {
    this.form = this.fb.group({
      phone: [null],
      password: [null],
    });
  }

  ngOnInit(): void {
  }

  submitForm(value): void {
    this.form.markAllAsTouched();
    this.form.updateValueAndValidity();
    console.log(value);
    this.srv.login(value).subscribe(res=>{
      if(res.isSuccess()){
        this.router.navigate(['./blog/home'])
      }
    })
    
  }

}
