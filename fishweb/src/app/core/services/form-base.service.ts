import { Injectable }   from '@angular/core';
import { FormControl, FormGroup, Validators, FormBuilder } from '@angular/forms';
import { FormBase } from '../model/form-base';


@Injectable()
export class InputControlService {
  constructor(private fb: FormBuilder) { }

  toFormGroup(questions: FormBase<string>[] ) {
    let group: any = {};

    group = questions.reduce((obj,v,i)=>{
        obj[v.key]=[v.value, v.valide]
        return obj
      },{})
    return this.fb.group(group);
  }
}