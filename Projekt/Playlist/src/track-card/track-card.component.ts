import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Track } from '../core/track.model';

@Component({
  standalone: true,
  selector: 'app-track-card',
  imports: [CommonModule],
  templateUrl: './track-card.component.html',
  styleUrls: ['./track-card.component.scss'],
})
export class TrackCardComponent {
  @Input() track!: Track;
  @Output() toggleLike = new EventEmitter<Track>();
  @Output() remove = new EventEmitter<Track>();
}
