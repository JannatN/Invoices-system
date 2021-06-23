import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BoardAuditorComponent } from './board-auditor.component';

describe('BoardAuditorComponent', () => {
  let component: BoardAuditorComponent;
  let fixture: ComponentFixture<BoardAuditorComponent>;

  beforeEach(async(() => {
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
