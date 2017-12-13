import { TestBed, inject } from '@angular/core/testing';

import { WallPostService } from './wall-post.service';

describe('WallPostService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [WallPostService]
    });
  });

  it('should be created', inject([WallPostService], (service: WallPostService) => {
    expect(service).toBeTruthy();
  }));
});
