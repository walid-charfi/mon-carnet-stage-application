jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { StageRadiologieService } from '../service/stage-radiologie.service';

import { StageRadiologieDeleteDialogComponent } from './stage-radiologie-delete-dialog.component';

describe('Component Tests', () => {
  describe('StageRadiologie Management Delete Component', () => {
    let comp: StageRadiologieDeleteDialogComponent;
    let fixture: ComponentFixture<StageRadiologieDeleteDialogComponent>;
    let service: StageRadiologieService;
    let mockActiveModal: NgbActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [StageRadiologieDeleteDialogComponent],
        providers: [NgbActiveModal],
      })
        .overrideTemplate(StageRadiologieDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(StageRadiologieDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(StageRadiologieService);
      mockActiveModal = TestBed.inject(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.close).toHaveBeenCalledWith('deleted');
        })
      ));

      it('Should not call delete service on clear', () => {
        // GIVEN
        spyOn(service, 'delete');

        // WHEN
        comp.cancel();

        // THEN
        expect(service.delete).not.toHaveBeenCalled();
        expect(mockActiveModal.close).not.toHaveBeenCalled();
        expect(mockActiveModal.dismiss).toHaveBeenCalled();
      });
    });
  });
});
