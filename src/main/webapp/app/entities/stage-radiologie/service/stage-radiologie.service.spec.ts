import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as dayjs from 'dayjs';

import { DATE_FORMAT, DATE_TIME_FORMAT } from 'app/config/input.constants';
import { AnneeEtude } from 'app/entities/enumerations/annee-etude.model';
import { Hopital } from 'app/entities/enumerations/hopital.model';
import { IStageRadiologie, StageRadiologie } from '../stage-radiologie.model';

import { StageRadiologieService } from './stage-radiologie.service';

describe('Service Tests', () => {
  describe('StageRadiologie Service', () => {
    let service: StageRadiologieService;
    let httpMock: HttpTestingController;
    let elemDefault: IStageRadiologie;
    let expectedResult: IStageRadiologie | IStageRadiologie[] | boolean | null;
    let currentDate: dayjs.Dayjs;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(StageRadiologieService);
      httpMock = TestBed.inject(HttpTestingController);
      currentDate = dayjs();

      elemDefault = {
        id: 0,
        anneeEtude: AnneeEtude.DCEM1,
        dateDebut: currentDate,
        dateFin: currentDate,
        hopital: Hopital.CharlesNicoles,
        chefService: 'AAAAAAA',
        semestre: 'AAAAAAA',
        groupe: 'AAAAAAA',
        objectif1: 'AAAAAAA',
        objectif1Enseigne: 'AAAAAAA',
        isObjectif1Evalue: false,
        noteObjectif1: 0,
        evaluationObjectifParObjectif: 0,
        evaluationRedactionObservation: 0,
        evaluationElaborationResume: 0,
        evaluationAttitudeAssiduite: 0,
        evaludationValidationGarde: 0,
        evaluationPeriodique: 0,
        eCOSM: 0,
        total: 0,
        isAbsenceSup10pc: false,
        isValide: false,
        dureeStageComplEnSemaine: 0,
        dateDebutStageCompl: currentDate,
        dateFinStageCompl: currentDate,
        isStageComplValide: false,
      };
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateDebut: currentDate.format(DATE_FORMAT),
            dateFin: currentDate.format(DATE_FORMAT),
            dateDebutStageCompl: currentDate.format(DATE_TIME_FORMAT),
            dateFinStageCompl: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a StageRadiologie', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateDebut: currentDate.format(DATE_FORMAT),
            dateFin: currentDate.format(DATE_FORMAT),
            dateDebutStageCompl: currentDate.format(DATE_TIME_FORMAT),
            dateFinStageCompl: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateDebut: currentDate,
            dateFin: currentDate,
            dateDebutStageCompl: currentDate,
            dateFinStageCompl: currentDate,
          },
          returnedFromService
        );

        service.create(new StageRadiologie()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a StageRadiologie', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            anneeEtude: 'BBBBBB',
            dateDebut: currentDate.format(DATE_FORMAT),
            dateFin: currentDate.format(DATE_FORMAT),
            hopital: 'BBBBBB',
            chefService: 'BBBBBB',
            semestre: 'BBBBBB',
            groupe: 'BBBBBB',
            objectif1: 'BBBBBB',
            objectif1Enseigne: 'BBBBBB',
            isObjectif1Evalue: true,
            noteObjectif1: 1,
            evaluationObjectifParObjectif: 1,
            evaluationRedactionObservation: 1,
            evaluationElaborationResume: 1,
            evaluationAttitudeAssiduite: 1,
            evaludationValidationGarde: 1,
            evaluationPeriodique: 1,
            eCOSM: 1,
            total: 1,
            isAbsenceSup10pc: true,
            isValide: true,
            dureeStageComplEnSemaine: 1,
            dateDebutStageCompl: currentDate.format(DATE_TIME_FORMAT),
            dateFinStageCompl: currentDate.format(DATE_TIME_FORMAT),
            isStageComplValide: true,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateDebut: currentDate,
            dateFin: currentDate,
            dateDebutStageCompl: currentDate,
            dateFinStageCompl: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a StageRadiologie', () => {
        const patchObject = Object.assign(
          {
            dateDebut: currentDate.format(DATE_FORMAT),
            dateFin: currentDate.format(DATE_FORMAT),
            hopital: 'BBBBBB',
            chefService: 'BBBBBB',
            semestre: 'BBBBBB',
            groupe: 'BBBBBB',
            evaluationObjectifParObjectif: 1,
            evaluationElaborationResume: 1,
            evaluationAttitudeAssiduite: 1,
            evaludationValidationGarde: 1,
            total: 1,
            isAbsenceSup10pc: true,
            isValide: true,
            dureeStageComplEnSemaine: 1,
          },
          new StageRadiologie()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            dateDebut: currentDate,
            dateFin: currentDate,
            dateDebutStageCompl: currentDate,
            dateFinStageCompl: currentDate,
          },
          returnedFromService
        );

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of StageRadiologie', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            anneeEtude: 'BBBBBB',
            dateDebut: currentDate.format(DATE_FORMAT),
            dateFin: currentDate.format(DATE_FORMAT),
            hopital: 'BBBBBB',
            chefService: 'BBBBBB',
            semestre: 'BBBBBB',
            groupe: 'BBBBBB',
            objectif1: 'BBBBBB',
            objectif1Enseigne: 'BBBBBB',
            isObjectif1Evalue: true,
            noteObjectif1: 1,
            evaluationObjectifParObjectif: 1,
            evaluationRedactionObservation: 1,
            evaluationElaborationResume: 1,
            evaluationAttitudeAssiduite: 1,
            evaludationValidationGarde: 1,
            evaluationPeriodique: 1,
            eCOSM: 1,
            total: 1,
            isAbsenceSup10pc: true,
            isValide: true,
            dureeStageComplEnSemaine: 1,
            dateDebutStageCompl: currentDate.format(DATE_TIME_FORMAT),
            dateFinStageCompl: currentDate.format(DATE_TIME_FORMAT),
            isStageComplValide: true,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateDebut: currentDate,
            dateFin: currentDate,
            dateDebutStageCompl: currentDate,
            dateFinStageCompl: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a StageRadiologie', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addStageRadiologieToCollectionIfMissing', () => {
        it('should add a StageRadiologie to an empty array', () => {
          const stageRadiologie: IStageRadiologie = { id: 123 };
          expectedResult = service.addStageRadiologieToCollectionIfMissing([], stageRadiologie);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(stageRadiologie);
        });

        it('should not add a StageRadiologie to an array that contains it', () => {
          const stageRadiologie: IStageRadiologie = { id: 123 };
          const stageRadiologieCollection: IStageRadiologie[] = [
            {
              ...stageRadiologie,
            },
            { id: 456 },
          ];
          expectedResult = service.addStageRadiologieToCollectionIfMissing(stageRadiologieCollection, stageRadiologie);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a StageRadiologie to an array that doesn't contain it", () => {
          const stageRadiologie: IStageRadiologie = { id: 123 };
          const stageRadiologieCollection: IStageRadiologie[] = [{ id: 456 }];
          expectedResult = service.addStageRadiologieToCollectionIfMissing(stageRadiologieCollection, stageRadiologie);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(stageRadiologie);
        });

        it('should add only unique StageRadiologie to an array', () => {
          const stageRadiologieArray: IStageRadiologie[] = [{ id: 123 }, { id: 456 }, { id: 703 }];
          const stageRadiologieCollection: IStageRadiologie[] = [{ id: 123 }];
          expectedResult = service.addStageRadiologieToCollectionIfMissing(stageRadiologieCollection, ...stageRadiologieArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const stageRadiologie: IStageRadiologie = { id: 123 };
          const stageRadiologie2: IStageRadiologie = { id: 456 };
          expectedResult = service.addStageRadiologieToCollectionIfMissing([], stageRadiologie, stageRadiologie2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(stageRadiologie);
          expect(expectedResult).toContain(stageRadiologie2);
        });

        it('should accept null and undefined values', () => {
          const stageRadiologie: IStageRadiologie = { id: 123 };
          expectedResult = service.addStageRadiologieToCollectionIfMissing([], null, stageRadiologie, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(stageRadiologie);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
