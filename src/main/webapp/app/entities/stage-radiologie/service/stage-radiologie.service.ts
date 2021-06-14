import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as dayjs from 'dayjs';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IStageRadiologie, getStageRadiologieIdentifier } from '../stage-radiologie.model';

export type EntityResponseType = HttpResponse<IStageRadiologie>;
export type EntityArrayResponseType = HttpResponse<IStageRadiologie[]>;

@Injectable({ providedIn: 'root' })
export class StageRadiologieService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/stage-radiologies');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(stageRadiologie: IStageRadiologie): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(stageRadiologie);
    return this.http
      .post<IStageRadiologie>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(stageRadiologie: IStageRadiologie): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(stageRadiologie);
    return this.http
      .put<IStageRadiologie>(`${this.resourceUrl}/${getStageRadiologieIdentifier(stageRadiologie) as number}`, copy, {
        observe: 'response',
      })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  partialUpdate(stageRadiologie: IStageRadiologie): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(stageRadiologie);
    return this.http
      .patch<IStageRadiologie>(`${this.resourceUrl}/${getStageRadiologieIdentifier(stageRadiologie) as number}`, copy, {
        observe: 'response',
      })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IStageRadiologie>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IStageRadiologie[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addStageRadiologieToCollectionIfMissing(
    stageRadiologieCollection: IStageRadiologie[],
    ...stageRadiologiesToCheck: (IStageRadiologie | null | undefined)[]
  ): IStageRadiologie[] {
    const stageRadiologies: IStageRadiologie[] = stageRadiologiesToCheck.filter(isPresent);
    if (stageRadiologies.length > 0) {
      const stageRadiologieCollectionIdentifiers = stageRadiologieCollection.map(
        stageRadiologieItem => getStageRadiologieIdentifier(stageRadiologieItem)!
      );
      const stageRadiologiesToAdd = stageRadiologies.filter(stageRadiologieItem => {
        const stageRadiologieIdentifier = getStageRadiologieIdentifier(stageRadiologieItem);
        if (stageRadiologieIdentifier == null || stageRadiologieCollectionIdentifiers.includes(stageRadiologieIdentifier)) {
          return false;
        }
        stageRadiologieCollectionIdentifiers.push(stageRadiologieIdentifier);
        return true;
      });
      return [...stageRadiologiesToAdd, ...stageRadiologieCollection];
    }
    return stageRadiologieCollection;
  }

  protected convertDateFromClient(stageRadiologie: IStageRadiologie): IStageRadiologie {
    return Object.assign({}, stageRadiologie, {
      dateDebut: stageRadiologie.dateDebut?.isValid() ? stageRadiologie.dateDebut.format(DATE_FORMAT) : undefined,
      dateFin: stageRadiologie.dateFin?.isValid() ? stageRadiologie.dateFin.format(DATE_FORMAT) : undefined,
      dateDebutStageCompl: stageRadiologie.dateDebutStageCompl?.isValid() ? stageRadiologie.dateDebutStageCompl.toJSON() : undefined,
      dateFinStageCompl: stageRadiologie.dateFinStageCompl?.isValid() ? stageRadiologie.dateFinStageCompl.toJSON() : undefined,
    });
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateDebut = res.body.dateDebut ? dayjs(res.body.dateDebut) : undefined;
      res.body.dateFin = res.body.dateFin ? dayjs(res.body.dateFin) : undefined;
      res.body.dateDebutStageCompl = res.body.dateDebutStageCompl ? dayjs(res.body.dateDebutStageCompl) : undefined;
      res.body.dateFinStageCompl = res.body.dateFinStageCompl ? dayjs(res.body.dateFinStageCompl) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((stageRadiologie: IStageRadiologie) => {
        stageRadiologie.dateDebut = stageRadiologie.dateDebut ? dayjs(stageRadiologie.dateDebut) : undefined;
        stageRadiologie.dateFin = stageRadiologie.dateFin ? dayjs(stageRadiologie.dateFin) : undefined;
        stageRadiologie.dateDebutStageCompl = stageRadiologie.dateDebutStageCompl ? dayjs(stageRadiologie.dateDebutStageCompl) : undefined;
        stageRadiologie.dateFinStageCompl = stageRadiologie.dateFinStageCompl ? dayjs(stageRadiologie.dateFinStageCompl) : undefined;
      });
    }
    return res;
  }
}
