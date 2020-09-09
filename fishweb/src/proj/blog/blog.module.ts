import { NgModule } from '@angular/core';
import { BlogListComponent } from './blog-list/blog-list.component';
import { BlogDetailComponent } from './blog-detail/blog-detail.component';
import { BlogEditComponent } from './blog-edit/blog-edit.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { BlogRoutingModule } from './blog-routing.module';
import { BlogHomeComponent } from './blog-home/blog-home.component';



@NgModule({
  declarations: [BlogListComponent, BlogDetailComponent, BlogEditComponent, BlogHomeComponent],
  imports: [
    SharedModule,
    BlogRoutingModule,
  ]
})
export class BlogModule { }
