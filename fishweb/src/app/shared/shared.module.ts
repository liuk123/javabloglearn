import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { SHARED_ZORRO_MODULES } from './shared-zorro';
import { DragDirective } from './directive/drop-drag/drag.directive';
import { DropDirective } from './directive/drop-drag/drop.directive';
import { DragDropService } from './directive/drop-drag/drag-drop.service';
import { InputNoSpaceDirective } from './directive/input/input-nospace.directive';
import { InputRequiredDirective } from './directive/input/input-require.directive';
import { InputTrimDirective } from './directive/input/input-trim.directive';
import { CarouselComponent } from './components/carousel/carousel.component';
import { UtilService } from './utils/util';
import { ListComponent } from './components/list/list.component';
import { TagsComponent } from './components/tags/tags.component';
import { BannerComponent } from './components/banner/banner.component';
import { CkeditorComponent } from './components/ckeditor/ckeditor.component';
import { CKEditorModule } from '@ckeditor/ckeditor5-angular';

//module
const THIRD_MODULES = [
  ...SHARED_ZORRO_MODULES,
  CKEditorModule
]
//component
const COMPONENTS = [
  CarouselComponent,
  ListComponent,
  TagsComponent,
  BannerComponent,
  CkeditorComponent,
]
//directive
const DIRECTIVES = [
  DragDirective,
  DropDirective,

  InputNoSpaceDirective,
  InputRequiredDirective,
  InputTrimDirective,
];
//pipes
const PIPES = [

];
//service
const SERVICE = [
  DragDropService,
  UtilService
]

@NgModule({
  declarations: [
    ...COMPONENTS,
    ...DIRECTIVES,
    ...PIPES,
  ],
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,
    ReactiveFormsModule,
    HttpClientModule,

    ...THIRD_MODULES,
  ],
  exports: [
    CommonModule,
    FormsModule,
    RouterModule,
    ReactiveFormsModule,

    ...THIRD_MODULES,
    ...COMPONENTS,
    ...DIRECTIVES,
    ...PIPES,
  ],
  providers: SERVICE
})
export class SharedModule { }
