import { Directive, HostListener, ElementRef, Renderer2, Input } from '@angular/core';
import { DragDropService } from './drag-drop.service';

@Directive({
  selector: '[app-draggable]',
})
export class DragDirective {

  private _isDraggable = false;

  @Input() dragTag: string;
  @Input() draggedClass: string;
  @Input() dragData: any;
  
  @Input('app-draggable')
  set isDraggable(draggable: boolean) {
    this._isDraggable = draggable;
    this.rd.setAttribute(this.el.nativeElement, 'draggable', `${draggable}`);
  }
  get isDraggable() {
    return this._isDraggable;
  }

  constructor(
    private el:ElementRef,
    private rd:Renderer2,
    private service:DragDropService) { }

    @HostListener('dragstart', ['$event'])//监听事件
    onDragStart(ev: Event) {
      if(this.el.nativeElement===ev.target){
        console.log('dragstart')
        this.rd.addClass(this.el.nativeElement, this.draggedClass);
        this.service.setDragData({tag: this.dragTag, data: this.dragData});
      }
    }
  
    @HostListener('dragend', ['$event'])
    onDragEnd(ev: Event) {
      if (this.el.nativeElement === ev.target) {
        console.log('dragend')
        this.rd.removeClass(this.el.nativeElement, this.draggedClass);
      }
    }

}
