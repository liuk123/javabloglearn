import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PersonHomeComponent } from './person-home/person-home.component';

const routes: Routes = [
    { path: '', redirectTo: 'home', pathMatch: 'full'},
    {
        path:'home',
        component: PersonHomeComponent,
        data:{
            keep:true,
        }
    },
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class PersonRoutingModule {}
