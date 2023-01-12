import { TestBed } from '@angular/core/testing';

import { LifeInsuranceService } from './life-insurance.service';

describe('LifeInsuranceService', () => {
  let service: LifeInsuranceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LifeInsuranceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
