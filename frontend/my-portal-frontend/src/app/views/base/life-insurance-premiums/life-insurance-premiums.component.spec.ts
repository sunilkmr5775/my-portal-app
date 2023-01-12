import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LifeInsurancePremiumsComponent } from './life-insurance-premiums.component';

describe('LifeInsurancePremiumsComponent', () => {
  let component: LifeInsurancePremiumsComponent;
  let fixture: ComponentFixture<LifeInsurancePremiumsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LifeInsurancePremiumsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LifeInsurancePremiumsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
