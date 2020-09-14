import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from 'src/app/biz/services/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.less']
})
export class RegisterComponent implements OnInit {
  form: FormGroup;

  constructor(
    private fb: FormBuilder,
    private srv:UserService,
    private router:Router) {
    this.form = this.fb.group({
      username: [''],
      phone: [''],
      password: [''],
    });
  }

  ngOnInit(): void {
  }

  submitForm(value): void {
    this.form.markAllAsTouched();
    this.form.updateValueAndValidity();
    console.log(value);
    this.srv.addUser(value).subscribe(res=>{
      if(res.isSuccess){
        this.router.navigate(['./blog/home']);
      }
    })


  }
}
