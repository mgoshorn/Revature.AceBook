import { TestBed, inject } from '@angular/core/testing';

import { FriendRequestService } from './friend-request.service';

describe('FriendRequest.ServiceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [FriendRequestService]
    });
  });

  it('should be created', inject([FriendRequestService], (service: FriendRequestService) => {
    expect(service).toBeTruthy();
  }));
});
