import { Directive, HostListener } from '@angular/core';
import {  NgControl } from '@angular/forms';

@Directive({
  selector: '[input-trim]'
})

export class InputTrimDirective {

    constructor( private control : NgControl) {}

    @HostListener("keyup", ["$event", "$event.target"]) 
    keyupFun(evt, target) {
      if (target.value) {
        this.control.control.setValue(this.trim(target.value));
      }
    }
    
    trim(str) {
      return str.replace(/(^\s*|\s*$)/g,"");
    }

}