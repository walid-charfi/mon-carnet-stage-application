jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { StageRadiologieService } from '../service/stage-radiologie.service';
import { IStageRadiologie, StageRadiologie } from '../stage-radiologie.model';

import { IUser } from 'app/entities/user/user.model';
import { UserService } from 'app/entities/user/user.service';

import { StageRadiologieUpdateComponent } from './stage-radiologie-update.component';

describe('Component Tests', () => {
  describe('StageRadiologie Management Update Component', () => {
    let comp: StageRadiologieUpdateComponent;
    let fixture: ComponentFixture<StageRadiologieUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let stageRadiologieService: StageRadiologieService;
    let userService: UserService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [StageRadiologieUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(StageRadiologieUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(StageRadiologieUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      stageRadiologieService = TestBed.inject(StageRadiologieService);
      userService = TestBed.inject(UserService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should call User query and add missing value', () => {
        const stageRadiologie: IStageRadiologie = { id: 456 };
        const user: IUser = { id: 27699 };
        stageRadiologie.user = user;

        const userCollection: IUser[] = [{ id: 87926 }];
        spyOn(userService, 'query').and.returnValue(of(new HttpResponse({ body: userCollection })));
        const additionalUsers = [user];
        const expectedCollection: IUser[] = [...additionalUsers, ...userCollection];
        spyOn(userService, 'addUserToCollectionIfMissing').and.returnValue(expectedCollection);

        activatedRoute.data = of({ stageRadiologie });
        comp.ngOnInit();

        expect(userService.query).toHaveBeenCalled();
        expect(userService.addUserToCollectionIfMissing).toHaveBeenCalledWith(userCollection, ...additionalUsers);
        expect(comp.usersSharedCollection).toEqual(expectedCollection);
      });

      it('Should update editForm', () => {
        const stageRadiologie: IStageRadiologie = { id: 456 };
        const user: IUser = { id: 47918 };
        stageRadiologie.user = user;

        activatedRoute.data = of({ stageRadiologie });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(stageRadiologie));
        expect(comp.usersSharedCollection).toContain(user);
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const stageRadiologie = { id: 123 };
        spyOn(stageRadiologieService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ stageRadiologie });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: stageRadiologie }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(stageRadiologieService.update).toHaveBeenCalledWith(stageRadiologie);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const stageRadiologie = new StageRadiologie();
        spyOn(stageRadiologieService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ stageRadiologie });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: stageRadiologie }));
        saveSubject.complete();

        // THEN
        expect(stageRadiologieService.create).toHaveBeenCalledWith(stageRadiologie);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const stageRadiologie = { id: 123 };
        spyOn(stageRadiologieService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ stageRadiologie });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(stageRadiologieService.update).toHaveBeenCalledWith(stageRadiologie);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });

    describe('Tracking relationships identifiers', () => {
      describe('trackUserById', () => {
        it('Should return tracked User primary key', () => {
          const entity = { id: 123 };
          const trackResult = comp.trackUserById(0, entity);
          expect(trackResult).toEqual(entity.id);
        });
      });
    });
  });
});
