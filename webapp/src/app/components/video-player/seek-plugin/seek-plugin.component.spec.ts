import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SeekPluginComponent } from './seek-plugin.component';

describe('SeekPluginComponent', () => {
  let component: SeekPluginComponent;
  let fixture: ComponentFixture<SeekPluginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SeekPluginComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SeekPluginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
