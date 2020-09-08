import { Directive,} from '@angular/core';
import { FormControl, NgControl } from '@angular/forms';

@Directive({
  selector: '[input-required]'
})
export class InputRequiredDirective {

    constructor(private control : NgControl) {
      if (control && control.control) {
        control.control.setValidators((c: FormControl) => {
            let v = c.value;
            if (!v || v.trim() == '') {
              return {'required': true};
            } 
            return null;
            });

        }

    }

}