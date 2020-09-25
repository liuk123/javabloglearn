import { NgModule } from '@angular/core';
import { BlogListComponent } from './blog-list/blog-list.component';
import { BlogDetailComponent } from './blog-detail/blog-detail.component';
import { BlogEditComponent } from './blog-edit/blog-edit.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { BlogRoutingModule } from './blog-routing.module';
import { BlogHomeComponent } from './blog-home/blog-home.component';
import { CommentComponent } from './blog-detail/comment/comment.component';
import { MyBlogComponent } from './my-blog/my-blog.component';
import { OperateListComponent } from './my-blog/operate-list/operate-list.component';



@NgModule({
  declarations: [BlogListComponent, BlogDetailComponent, BlogEditComponent, BlogHomeComponent, CommentComponent, OperateListComponent, MyBlogComponent],
  imports: [
    SharedModule,
    BlogRoutingModule,
  ]
})
export class BlogModule { }
