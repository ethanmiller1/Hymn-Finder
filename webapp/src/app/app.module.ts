import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import {RouterModule, Routes} from "@angular/router";
import { SermonListComponent } from './components/sermon-list/sermon-list.component';

const routes: Routes = [
  {path: 'sermons', component: SermonListComponent},
  {path: '', redirectTo: '/sermons', pathMatch: 'full'},
  {path: '**', redirectTo: '/sermons', pathMatch: 'full'}
];

@NgModule({
  declarations: [
    AppComponent,
    SermonListComponent
  ],
    imports: [
        RouterModule.forRoot(routes),
        BrowserModule,
        RouterModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
