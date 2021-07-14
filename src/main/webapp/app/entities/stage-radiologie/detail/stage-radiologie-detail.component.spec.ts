import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { StageRadiologieDetailComponent } from './stage-radiologie-detail.component';

describe('Component Tests', () => {
  describe('StageRadiologie Management Detail Component', () => {
    let comp: StageRadiologieDetailComponent;
    let fixture: ComponentFixture<StageRadiologieDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [StageRadiologieDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ stageRadiologie: { id: 123 } }) },
          },
        ],
      })
        .overrideTemplate(StageRadiologieDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(StageRadiologieDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load stageRadiologie on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.stageRadiologie).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
