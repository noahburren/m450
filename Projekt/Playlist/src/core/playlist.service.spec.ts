import { PlaylistService } from './playlist.service';

describe('PlaylistService', () => {
  it('returns only liked tracks sorted by artist then title', () => {
    const s = new PlaylistService();
    const data = [
      { title: 'B', artist: 'Z', liked: false, durationSec: 180 },
      { title: 'A', artist: 'B', liked: true,  durationSec: 200 },
      { title: 'C', artist: 'B', liked: true,  durationSec: 220 }
    ];
    const res = s.likedSorted(data);
    expect(res).toHaveLength(2);
    expect(res[0]).toMatchObject({ artist: 'B', title: 'A' });
    expect(res[1]).toMatchObject({ artist: 'B', title: 'C' });
  });
});
