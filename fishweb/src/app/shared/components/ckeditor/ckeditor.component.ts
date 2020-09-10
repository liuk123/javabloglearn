import { Component, OnInit, forwardRef, Input } from '@angular/core';
import { NG_VALUE_ACCESSOR, NG_VALIDATORS, FormControl, ControlValueAccessor } from '@angular/forms';
import * as DecoupledEditor from '@ckeditor/ckeditor5-build-decoupled-document';
import { ChangeEvent } from '@ckeditor/ckeditor5-angular';

@Component({
  selector: 'app-ckeditor',
  templateUrl: './ckeditor.component.html',
  styleUrls: ['./ckeditor.component.less'],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(()=>CkeditorComponent),
      multi:true
    },
    {
      provide: NG_VALIDATORS,
      useExisting: forwardRef(()=>CkeditorComponent),
      multi:true//令牌多对一
    }
  ]
})
export class CkeditorComponent implements ControlValueAccessor {

  public Editor = DecoupledEditor;

  editorData =''
  @Input() isDisabled = false
  config = {
    language: 'zh-cn',
    placeholder: 'Type the content here!',
    toolbar: [ 'heading', '|', 'bold', 'italic' ] }
  private propageteChange=(_:any)=>{};

  constructor() { }

  ngOnInit(){
    
  }

  writeValue(obj:any):void{
    this.editorData=obj;
  }
  registerOnChange(fn:any):void{
    this.propageteChange=fn;
  }
  registerOnTouched(fn:any):void{

  }

  validate(c:FormControl):{[key:string]:any}{
    return this.editorData?null:{
      editorInvalid:{
        valid:false
      }
    }
  }


  
  onReady( editor ) {
    editor.ui.getEditableElement().parentElement.insertBefore(
      editor.ui.view.toolbar.element,
      editor.ui.getEditableElement()
    );    
  }
  onChange( { editor }: ChangeEvent ) {
    const data = editor.getData();
    this.propageteChange(data)
  }
}