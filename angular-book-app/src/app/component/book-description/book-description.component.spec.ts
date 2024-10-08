import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookDescriptionComponent } from './book-description.component';

describe('BookDescriptionComponent', () => {
  let component: BookDescriptionComponent;
  let fixture: ComponentFixture<BookDescriptionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BookDescriptionComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BookDescriptionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
