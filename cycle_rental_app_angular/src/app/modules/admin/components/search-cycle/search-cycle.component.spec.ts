import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchCycleComponent } from './search-cycle.component';

describe('SearchCycleComponent', () => {
  let component: SearchCycleComponent;
  let fixture: ComponentFixture<SearchCycleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SearchCycleComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SearchCycleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
