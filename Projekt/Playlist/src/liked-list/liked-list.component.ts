import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Track } from '../core/playlist.service';

@Component({
  standalone: true,
  selector: 'app-liked-list',
  imports: [CommonModule],
  template: `
    <h1>Liked</h1>
    <ul>
      <li *ngFor="let t of tracks">{{ t.artist }} – {{ t.title }}</li>
    </ul>
  `
})
export class LikedListComponent {
  @Input() tracks: Track[] = [];
}
