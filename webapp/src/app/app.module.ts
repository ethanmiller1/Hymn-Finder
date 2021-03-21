import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import {RouterModule, Routes} from "@angular/router";
import { SermonListComponent } from './components/sermon-list/sermon-list.component';
import {HttpClientModule} from "@angular/common/http";
import {SermonService} from "./services/sermon.service";
import { HeaderComponent } from './components/video-page/header/header.component';
import { NavComponent } from './components/video-page/nav/nav.component';
import { ContentComponent } from './components/video-page/content/content.component';
import { MainBannerComponent } from './components/video-page/main-banner/main-banner.component';

const routes: Routes = [
  {path: 'sermons', component: SermonListComponent},
  {path: '', redirectTo: '/sermons', pathMatch: 'full'},
  {path: '**', redirectTo: '/sermons', pathMatch: 'full'}
];

@NgModule({
  declarations: [
    AppComponent,
    SermonListComponent,
    HeaderComponent,
    NavComponent,
    ContentComponent,
    MainBannerComponent
  ],
    imports: [
        RouterModule.forRoot(routes),
        BrowserModule,
        HttpClientModule,
        RouterModule
    ],
  providers: [SermonService],
  bootstrap: [AppComponent]
})
export class AppModule { }
