import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostCycleComponent } from './post-cycle.component';

describe('PostCycleComponent', () => {
  let component: PostCycleComponent;
  let fixture: ComponentFixture<PostCycleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PostCycleComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PostCycleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
