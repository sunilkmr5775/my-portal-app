import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DatabaseInformationComponent } from './database-information.component';

describe('DatabaseInformationComponent', () => {
  let component: DatabaseInformationComponent;
  let fixture: ComponentFixture<DatabaseInformationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DatabaseInformationComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DatabaseInformationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
