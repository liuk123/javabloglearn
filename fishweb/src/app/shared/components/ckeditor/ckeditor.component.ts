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
      multi:true
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
    // toolbar: [ 'heading', '|', 'bold', 'italic' ] 
  }
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
    editor.plugins.get( 'FileRepository' ).createUploadAdapter = function( loader ) {
      return new FileUploadAdapter(loader);
    };
  }

  onChange( { editor }: ChangeEvent ) {
    const data = editor.getData();
    this.propageteChange(data)
  }
}


class FileUploadAdapter {
	
	constructor(private loader) {
	}
	
	upload() {
		return new Promise((resolve, reject) => {
			const data = new FormData();
			data.append('file', this.loader.file);
      
			var xhr = new XMLHttpRequest();
			// xhr.setRequestHeader("Content-type","multipart/form-data");
			xhr.open('post', '/api/uploadpic' );
			xhr.send(data);
			xhr.onreadystatechange = function () {
				if (xhr.readyState == 4 && xhr.status == 200) {
					let data=JSON.parse(xhr.responseText);
					if(data.fileName){
						resolve({
							default:'/api'+data.fileName
						});
					}else {
						reject(data.msg);
					}
				} 
			};
		});
	}
	abort() {
	}
}