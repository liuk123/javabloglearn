import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BlogListComponent } from './blog-list/blog-list.component';
import { BlogDetailComponent } from './blog-detail/blog-detail.component';
import { BlogEditComponent } from './blog-edit/blog-edit.component';

const routes: Routes = [
    { path: '', redirectTo: 'list', pathMatch: 'full'},
    {
        path:'list',
        component: BlogListComponent,
        data:{
            keep:true,
        }
    },{
        path:'detail',
        component: BlogDetailComponent,
        data:{
            keep:true,
        }
    },{
        path:'edit',
        component: BlogEditComponent,
        data:{
            keep:true,
        }
    },
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
})
export class BlogRoutingModule {}
