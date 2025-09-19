export interface Track { title: string; artist: string; liked?: boolean; durationSec: number; }

export class PlaylistService {
  likedSorted(tracks: Track[]): Track[] {
    return tracks
      .filter(t => t.liked)
      .sort((a, b) => a.artist.localeCompare(b.artist) || a.title.localeCompare(b.title));
  }
}

