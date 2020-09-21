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
      useExisting: forwardRef(() => CkeditorComponent),
      multi: true
    },
    {
      provide: NG_VALIDATORS,
      useExisting: forwardRef(() => CkeditorComponent),
      multi: true
    }
  ]
})
export class CkeditorComponent implements ControlValueAccessor {

  public Editor = DecoupledEditor;

  editorData = ''
  @Input() isDisabled = false
  config = {
  }
  private propageteChange = (_: any) => { };

  constructor() { }

  ngOnInit() {

  }

  writeValue(obj: any): void {
    this.editorData = obj;
  }
  registerOnChange(fn: any): void {
    this.propageteChange = fn;
  }
  registerOnTouched(fn: any): void {

  }

  validate(c: FormControl): { [key: string]: any } {
    return this.editorData ? null : {
      editorInvalid: {
        valid: false
      }
    }
  }

  onReady(editor) {
    editor.ui.getEditableElement().parentElement.insertBefore(
      editor.ui.view.toolbar.element,
      editor.ui.getEditableElement()
    );
    editor.plugins.get('FileRepository').createUploadAdapter = function (loader) {
      return new FileUploadAdapter(loader);
    };
  }

  onChange({ editor }: ChangeEvent) {
    // const data = editor.getData();
    this.propageteChange(this.editorData);
  }
}


class FileUploadAdapter {
  xhr = new XMLHttpRequest();
  constructor(private loader) {
  }

  upload() {

    return this.loader.file.then(file => new Promise((resolve, reject) => {
      this._initRequest();
      this._initListeners(resolve, reject, file);
      this._sendRequest(file);
    }))
  }
  abort() {
    if (this.xhr) {
      this.xhr.abort();
    }
  }

  _initRequest() {
    const xhr = this.xhr;
    xhr.open('POST', '/file/', true);
  }

  _initListeners(resolve, reject, file) {
    const xhr = this.xhr;
    const loader = this.loader;
    const genericErrorText = `Couldn't upload file: ${file.name}.`;

    xhr.addEventListener('error', () => reject(genericErrorText));
    xhr.addEventListener('abort', () => reject());
    xhr.addEventListener('load', () => {
      const response = xhr.response;

      if (!response || response.error) {
        return reject(response && response.error ? response.error.message : genericErrorText);
      }
      resolve({
        default: response.url
      });
    });

    if (xhr.upload) {
      xhr.upload.addEventListener('progress', evt => {
        if (evt.lengthComputable) {
          loader.uploadTotal = evt.total;
          loader.uploaded = evt.loaded;
        }
      });
    }
  }
  _sendRequest( file ) {
    const data = new FormData();
    data.append( 'uploadFile', file );
    this.xhr.send( data );
  }
}