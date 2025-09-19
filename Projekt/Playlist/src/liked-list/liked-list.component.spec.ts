import { TestBed } from '@angular/core/testing';
import { LikedListComponent } from './liked-list.component';

describe('LikedListComponent', () => {
  it('renders liked tracks list', async () => {
    await TestBed.configureTestingModule({ imports: [LikedListComponent] }).compileComponents();
    const fixture = TestBed.createComponent(LikedListComponent);
    fixture.componentInstance.tracks = [
      { title: 'A', artist: 'B', liked: true, durationSec: 200 },
      { title: 'C', artist: 'B', liked: true, durationSec: 220 }
    ];
    fixture.detectChanges();
    const el: HTMLElement = fixture.nativeElement;
    expect(el.querySelector('h1')?.textContent).toContain('Liked');
    expect(el.querySelectorAll('li').length).toBe(2);
    expect(el.querySelector('li')?.textContent).toContain('B – A');
  });
});
