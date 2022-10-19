import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MiniPaymentsComponent } from './mini-payments.component';

describe('MiniPaymentsComponent', () => {
  let component: MiniPaymentsComponent;
  let fixture: ComponentFixture<MiniPaymentsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MiniPaymentsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MiniPaymentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
