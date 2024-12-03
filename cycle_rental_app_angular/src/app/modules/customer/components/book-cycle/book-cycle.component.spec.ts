import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookCycleComponent } from './book-cycle.component';

describe('BookCycleComponent', () => {
  let component: BookCycleComponent;
  let fixture: ComponentFixture<BookCycleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BookCycleComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BookCycleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
