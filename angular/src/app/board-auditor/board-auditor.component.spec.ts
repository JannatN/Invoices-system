import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { BoardAuditorComponent } from './board-auditor.component';

describe('BoardAuditorComponent', () => {
  let component: BoardAuditorComponent;
  let fixture: ComponentFixture<BoardAuditorComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ BoardAuditorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BoardAuditorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
