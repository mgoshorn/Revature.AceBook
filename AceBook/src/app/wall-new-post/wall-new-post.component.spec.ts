import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WallNewPostComponent } from './wall-new-post.component';

describe('WallNewPostComponent', () => {
  let component: WallNewPostComponent;
  let fixture: ComponentFixture<WallNewPostComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WallNewPostComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WallNewPostComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
