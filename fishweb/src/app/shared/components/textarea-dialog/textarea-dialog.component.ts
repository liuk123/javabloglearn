import { Component, OnDestroy, OnInit } from '@angular/core';
import { NzModalRef } from 'ng-zorro-antd/modal';

@Component({
  selector: 'app-textarea-dialog',
  templateUrl: './textarea-dialog.component.html',
  styleUrls: ['./textarea-dialog.component.less']
})
export class TextareaDialogComponent implements OnInit {

  inputValue="";
  constructor(private modal: NzModalRef) { }

  ngOnInit(): void {
  }
  
  destroyModal(){
    
  }

}
