import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { NzModalService } from 'ng-zorro-antd/modal';
import { TextareaDialogComponent } from 'src/app/shared/components/textarea-dialog/textarea-dialog.component';

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.less']
})
export class CommentComponent implements OnInit {

  @Input() data;
  @Output() commentEvent = new EventEmitter();
  @Output() replyEvent = new EventEmitter();

  @Input() submitting;
  @Output() submittingChange = new EventEmitter();

  inputValue="";
  constructor(
    private modal: NzModalService,
  ) { }

  ngOnInit(): void {
  }

  handleSubmit(){
    this.commentEvent.emit(this.inputValue);
  }
  replySubmit(commentId,toUserId,toUsername){
    const modal = this.modal.create({
      nzTitle: 'reply',
      nzContent: TextareaDialogComponent,
      nzOnOk:()=>{
        const instance = modal.getContentComponent();
        this.replyEvent.emit({
          content: instance.inputValue,
          commentId,
          toUserId,
          toUsername,
        });
      },
    });
  }
}
