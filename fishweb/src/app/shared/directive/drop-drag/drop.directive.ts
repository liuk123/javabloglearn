import { Directive, HostListener, ElementRef, Renderer2, Input,　Output , EventEmitter} from '@angular/core';
import { DragDropService, DragData } from './drag-drop.service';
import { take } from 'rxjs/operators';

@Directive({
  selector: '[app-droppable]',
})
export class DropDirective {
  @Output() dropped: EventEmitter<DragData> = new EventEmitter();
  @Input('dragEnterClass') dragEnterClass:string='';
  @Input('dropTags') dropTags:string[]=[];
  private drag$;
  constructor(
    private el:ElementRef,
    private rd:Renderer2,
    private service:DragDropService) {
      this.drag$ = this.service.getDragData().pipe(take(1))
    }

  @HostListener('dragenter', ['$event'])
  onDragEnter(ev: Event) {
    ev.preventDefault();
    ev.stopPropagation();
    if (this.el.nativeElement === ev.target) {
      this.drag$.subscribe(dragData => {
        if (this.dropTags.indexOf(dragData.tag) > -1) {
          console.log('dragenter');
          this.rd.addClass(this.el.nativeElement, this.dragEnterClass);
        }
      });
    }
  }

  @HostListener('dragover', ['$event'])
  onDragOver(ev: Event) {
    ev.preventDefault();
    ev.stopPropagation();
    if (this.el.nativeElement === ev.target) {
      console.log('dragover');
      this.drag$.subscribe(dragData => {
        //设置dom元素属性
        // if (this.dropTags.indexOf(dragData.tag) > -1) {
        //   this.rd.setProperty(ev, 'dataTransfer.effectAllowed', 'all');
        //   this.rd.setProperty(ev, 'dataTransfer.dropEffect', 'move');
        // } else {
        //   this.rd.setProperty(ev, 'dataTransfer.effectAllowed', 'none');
        //   this.rd.setProperty(ev, 'dataTransfer.dropEffect', 'none');
        // }
      });
    }
  }

  @HostListener('dragleave', ['$event'])
  onDragLeave(ev: Event) {
    ev.preventDefault();
    ev.stopPropagation();
    if (this.el.nativeElement === ev.target) {
      this.drag$.subscribe(dragData => {
        if (this.dropTags.indexOf(dragData.tag) > -1) {
          console.log('dragleave');
          this.rd.removeClass(this.el.nativeElement, this.dragEnterClass);
        }
      });
    }
  }

  @HostListener('drop', ['$event'])
  onDrop(ev: Event) {
    ev.preventDefault();
    ev.stopPropagation();
    if (this.el.nativeElement === ev.target) {
    this.drag$.subscribe(dragData => {
      console.log('dragData');
      if (this.dropTags.indexOf(dragData.tag) > -1) {
        this.rd.removeClass(this.el.nativeElement, this.dragEnterClass);
        this.dropped.emit(dragData);
        this.service.clearDragData();
      }
    });
    }
  }

}
