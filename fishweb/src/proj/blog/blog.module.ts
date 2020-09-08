import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BlogListComponent } from './blog-list/blog-list.component';
import { BlogDetailComponent } from './blog-detail/blog-detail.component';
import { BlogEditComponent } from './blog-edit/blog-edit.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { BlogRoutingModule } from './blog-routing.module';
import { BlogHomeComponent } from './blog-home/blog-home.component';
import { CarouselComponent } from './blog-home/carousel/carousel.component';



@NgModule({
  declarations: [BlogListComponent, BlogDetailComponent, BlogEditComponent, BlogHomeComponent, CarouselComponent],
  imports: [
    SharedModule,
    BlogRoutingModule,
  ]
})
export class BlogModule { }
