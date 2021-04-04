import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import {RouterModule, Routes} from '@angular/router';
import { SermonListComponent } from './components/sermon-list/sermon-list.component';
import {HttpClientModule} from '@angular/common/http';
import {SermonService} from './services/sermon.service';
import { HeaderComponent } from './components/partials/header/header.component';
import { NavComponent } from './components/partials/nav/nav.component';
import { ContentComponent } from './components/partials/content/content.component';
import { MainBannerComponent } from './components/partials/main-banner/main-banner.component';
import { HomeComponent } from './components/home/home.component';
import { VideoPageComponent } from './components/video-page/video-page.component';

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'sermons', component: SermonListComponent},
  {path: 'sermons/:id', component: VideoPageComponent},
  {path: '**', redirectTo: '/sermons', pathMatch: 'full'}
];

@NgModule({
  declarations: [
    AppComponent,
    SermonListComponent,
    HeaderComponent,
    NavComponent,
    ContentComponent,
    MainBannerComponent,
    HomeComponent,
    VideoPageComponent
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
