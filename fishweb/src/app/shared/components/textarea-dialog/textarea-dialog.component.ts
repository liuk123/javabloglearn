import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-textarea-dialog',
  template: '<textarea rows="4" nz-input [(ngModel)]="inputValue"></textarea>',
})
export class TextareaDialogComponent{

  inputValue="";
  constructor() { }

}
