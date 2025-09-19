import { Routes } from '@angular/router';
import {LikedListComponent} from '../liked-list/liked-list.component';
import {AppComponent} from './app.component';

export const routes: Routes = [
  {
    path: '',
    component: AppComponent,
    title: 'My Playlist'
  },
  {
    path: 'liked',
    component: LikedListComponent,
    title: 'Liked Tracks'
  },
  {
    path: '**',
    redirectTo: ''
  }
];
