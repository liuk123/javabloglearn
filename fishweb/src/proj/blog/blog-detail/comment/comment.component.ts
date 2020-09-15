import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.less']
})
export class CommentComponent implements OnInit {

  @Input() data;
  @Output() commentEvent = new EventEmitter();

  @Input() submitting;
  @Output() submittingChange = new EventEmitter();

  inputValue="";
  
  constructor() { }

  ngOnInit(): void {
  }

  handleSubmit(){
    this.commentEvent.emit(this.inputValue);
  }
}
