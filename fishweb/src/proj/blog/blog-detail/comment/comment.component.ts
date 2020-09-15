import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { DialogService } from 'src/app/core/services/dialog.service';
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
    private dialogSrv: DialogService,
  ) { }

  ngOnInit(): void {
  }

  handleSubmit(){
    this.commentEvent.emit(this.inputValue);
  }
  replySubmit(){
    this.dialogSrv.createModal('回复', TextareaDialogComponent, {});
  }
}
